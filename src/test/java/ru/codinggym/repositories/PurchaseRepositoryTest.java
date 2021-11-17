package ru.codinggym.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.codinggym.util.TestUtility;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class PurchaseRepositoryTest {

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
    void getPurchaseSumByUserLastName() {
        Double purchaseSumByUserId = purchaseRepository.getPurchaseSumByUserLastName("Testov1");

        assertEquals(7, purchaseSumByUserId);
    }
}