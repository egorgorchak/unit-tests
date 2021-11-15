package ru.codinggym.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.codinggym.repositories.PurchaseRepository;
import ru.codinggym.util.TestUtility;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class PurchaseRepositoryTest {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @BeforeEach
    public void setUp() {
        purchaseRepository.saveAll(TestUtility.getTestPurchases());
    }

    @AfterEach
    public void tearDown() throws Exception {
        purchaseRepository.deleteAll();
    }

    @Test
    public void getPurchaseSumByUserId() {
        Double purchaseSumByUserId = purchaseRepository.getPurchaseSumByUserLastName("Testov1");

        assertEquals(7, purchaseSumByUserId);
    }
}