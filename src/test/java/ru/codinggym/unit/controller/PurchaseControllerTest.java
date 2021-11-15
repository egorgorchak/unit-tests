package ru.codinggym.unit.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.codinggym.controllers.PurchaseController;
import ru.codinggym.unit.service.PurchaseService;
import ru.codinggym.util.TestUtility;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PurchaseController.class)
public class PurchaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PurchaseService purchaseService;

    @Test
    public void allPurchases() throws Exception {
        BDDMockito.when(purchaseService.getAllPurchasesFromDB()).thenReturn(TestUtility.getTestPurchases());

        mockMvc.perform(get("/purchases"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().json("[{\"purchaseId\":null,\"category\":\"FUN\",\"content\":\"test1\",\"purchaseSum\":0.0},{\"purchaseId\":null,\"category\":\"CLOTHES\",\"content\":\"test2\",\"purchaseSum\":1.0},{\"purchaseId\":null,\"category\":\"FUN\",\"content\":\"test3\",\"purchaseSum\":2.0},{\"purchaseId\":null,\"category\":\"MUN_SERVICES\",\"content\":\"test3\",\"purchaseSum\":2.0},{\"purchaseId\":null,\"category\":\"MUN_SERVICES\",\"content\":\"test3\",\"purchaseSum\":2.0},{\"purchaseId\":null,\"category\":\"FOOD\",\"content\":\"test4\",\"purchaseSum\":3.0},{\"purchaseId\":null,\"category\":\"FUN\",\"content\":\"test5\",\"purchaseSum\":4.0}]"));

    }
}