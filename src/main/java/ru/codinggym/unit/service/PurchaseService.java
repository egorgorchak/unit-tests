package ru.codinggym.unit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.codinggym.model.Purchase;
import ru.codinggym.model.PurchaseCategory;
import ru.codinggym.model.User;
import ru.codinggym.repositories.PurchaseRepository;
import ru.codinggym.repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final CurrencyService currencyService;

    public Purchase savePurchase(Purchase purchase) {
        User user = userRepository.findById(purchase.getUser().getUserId()).get();
        purchase.setUser(user);
        return purchaseRepository.save(purchase);
    }

    public List<Purchase> getAllPurchasesFromDB() {
        return (List<Purchase>) purchaseRepository.findAll();
    }

    public String getMostPopularPurchaseCategory() {
        return "The most popular category is: " + calculatePurchaseCategory(getAllPurchasesFromDB());
    }

    public String getMostPopularPurchaseCategoryByUser(String lastName) {
        PurchaseCategory mostPopularCategory = calculatePurchaseCategory(getAllPurchasesFromDB().stream()
                .filter(el -> Objects.equals(el.getUser().getLastName(), lastName))
                .collect(Collectors.toList())
        );
        return lastName + " most popular category is: " + mostPopularCategory;
    }

    public String getSumOfPurchases(String lastName) {
        Double purchaseSumByUserId = purchaseRepository.getPurchaseSumByUserLastName(lastName);
        if (purchaseSumByUserId == null) {
            purchaseSumByUserId = 0d;
        }
        Double latestCurrency = Double.parseDouble(currencyService.getLatestCurrency());
        return lastName + " sum of purchases " + purchaseSumByUserId * latestCurrency;
    }

    private PurchaseCategory calculatePurchaseCategory(List<Purchase> allPurchasesFromDB) {
        Map<PurchaseCategory, Integer> integerPurchaseCategoryHashMap = new HashMap<>();
        for (Purchase purchase : allPurchasesFromDB) {
            PurchaseCategory category = purchase.getCategory();
            if (integerPurchaseCategoryHashMap.containsKey(category)) {
                Integer integer = integerPurchaseCategoryHashMap.get(category) + 1;
                integerPurchaseCategoryHashMap.put(category, integer);
            } else {
                integerPurchaseCategoryHashMap.put(category, 1);
            }
        }
        return integerPurchaseCategoryHashMap.entrySet()
                .stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .get();
    }

}

