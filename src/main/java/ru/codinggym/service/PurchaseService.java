package ru.codinggym.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.codinggym.model.Purchase;
import ru.codinggym.model.PurchaseCategory;
import ru.codinggym.model.User;
import ru.codinggym.repositories.PurchaseRepository;
import ru.codinggym.repositories.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
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

    public String getMostPopularPurchaseCategoryByUser(Long userId) {
        PurchaseCategory mostPopularCategory = calculatePurchaseCategory(getAllPurchasesFromDB().stream()
                .filter(el -> Objects.equals(el.getUser().getUserId(), userId))
                .collect(Collectors.toList())
        );
        return getUserName(userId) + " most popular category is:" + mostPopularCategory;
    }

    public String getSumOfPurchases(Long userId) {
        Double purchaseSumByUserId = purchaseRepository.getPurchaseSumByUserId(userId);
        if (purchaseSumByUserId == null) {
            purchaseSumByUserId = 0d;
        }
        Double latestCurrency = Double.parseDouble(currencyService.getLatestCurrency());
        return getUserName(userId) + " sum of purchases " + purchaseSumByUserId * latestCurrency;
    }

    private String getUserName(Long userId) {
        User user = userRepository.findById(userId).get();
        return user.getFirstName() + " " + user.getLastName();
    }

    private PurchaseCategory calculatePurchaseCategory(List<Purchase> allPurchasesFromDB) {
        return allPurchasesFromDB.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max((el1, el2) -> (int) (el1.getValue() - el2.getValue()))
                .map(el -> el.getKey().getCategory())
                .orElseThrow(() -> new RuntimeException("There is now such thing!"));
    }

}

