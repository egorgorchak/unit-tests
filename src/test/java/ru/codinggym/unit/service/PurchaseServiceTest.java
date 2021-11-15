package ru.codinggym.unit.service;

import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.codinggym.model.Purchase;
import ru.codinggym.model.User;
import ru.codinggym.repositories.PurchaseRepository;
import ru.codinggym.util.TestUtility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseServiceTest {

    private final User testUser = TestUtility.getTestUser();
    private final List<Purchase> purchases = TestUtility.getTestPurchases();
    @Autowired
    private PurchaseService purchaseService;
    @MockBean
    private PurchaseRepository purchaseRepository;
    @MockBean
    private CurrencyService currencyService;

    @Test
    public void getMostPopularPurchaseCategory() {
        when(purchaseRepository.findAll()).thenReturn(purchases);

        String mostPopularPurchaseCategory = purchaseService.getMostPopularPurchaseCategory();
        assertEquals("The most popular category is: FUN", mostPopularPurchaseCategory);
    }

    @Test
    public void getMostPopularPurchaseCategoryByUser() {
        when(purchaseRepository.findAll()).thenReturn(purchases);

        String mostPopularPurchaseCategoryByUser = purchaseService.getMostPopularPurchaseCategoryByUser("Testov1");
        assertEquals("Testov1 most popular category is: MUN_SERVICES", mostPopularPurchaseCategoryByUser);
    }

    @Test
    public void getSumOfPurchases() {
        when(purchaseRepository.getPurchaseSumByUserLastName("Testov1")).thenReturn(100d);
        when(currencyService.getLatestCurrency()).thenReturn("80");

        String sumOfPurchases = purchaseService.getSumOfPurchases("Testov1");
        assertEquals("Testov1 sum of purchases 8000.0", sumOfPurchases);
    }

}