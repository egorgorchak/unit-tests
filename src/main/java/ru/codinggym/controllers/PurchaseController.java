package ru.codinggym.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.codinggym.model.Purchase;
import ru.codinggym.service.PurchaseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping("/save")
    public ResponseEntity<Purchase> savePurchase(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.savePurchase(purchase), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> allPurchases() {
        return new ResponseEntity<>(purchaseService.getAllPurchasesFromDB(), HttpStatus.OK);
    }

    @GetMapping("/popular")
    public ResponseEntity<String> mostPopularCategory() {
        return new ResponseEntity<>(purchaseService.getMostPopularPurchaseCategory(), HttpStatus.OK);
    }

    @GetMapping("/popular/{userId}")
    public ResponseEntity<String> mostPopularCategoryByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(purchaseService.getMostPopularPurchaseCategoryByUser(Long.parseLong(userId)), HttpStatus.OK);
    }

    @GetMapping("/sum/{userId}")
    public ResponseEntity<String> sumOfPurchases(@PathVariable String userId) {
        return new ResponseEntity<>(purchaseService.getSumOfPurchases(Long.parseLong(userId)), HttpStatus.OK);
    }
}
