package ru.codinggym.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.codinggym.model.Purchase;
import ru.codinggym.service.PurchaseService;

import java.util.List;

@RestController
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/purchases/save")
    public ResponseEntity<Purchase> savePurchase(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.savePurchase(purchase), HttpStatus.OK);
    }

    @GetMapping("/purchases")
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        return new ResponseEntity<>(purchaseService.getAllPurchasesFromDB(), HttpStatus.OK);
    }
}
