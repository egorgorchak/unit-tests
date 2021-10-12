package ru.codinggym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.codinggym.model.Purchase;
import ru.codinggym.model.User;
import ru.codinggym.repositories.PurchaseRepository;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private UserService userService;

    public Purchase savePurchase(Purchase purchase) {
        User user = userService.getUserByID(purchase.getUser().getUserId()).get();
        purchase.setUser(user);
        return purchaseRepository.save(purchase);
    }

    public List<Purchase> getAllPurchasesFromDB() {
        return (List<Purchase>) purchaseRepository.findAll();
    }
}
