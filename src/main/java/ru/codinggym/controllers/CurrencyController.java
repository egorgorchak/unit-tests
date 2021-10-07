package ru.codinggym.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.codinggym.service.CurrencyService;

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/exchange")
    public String currency(@RequestParam String currency) {
        return currencyService.getLatestCurrency(currency);
    }
}
