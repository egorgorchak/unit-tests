package ru.codinggym.util;

import com.sun.tools.javac.util.List;
import org.springframework.util.ResourceUtils;
import ru.codinggym.model.Purchase;
import ru.codinggym.model.PurchaseCategory;
import ru.codinggym.model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;

public class TestUtility {
    public static String getFileFromClasspath(String path) throws IOException {
        File file = ResourceUtils.getFile(path);
        return new String(Files.readAllBytes(file.toPath()));
    }

    public static User getTestUser() {
        return new User("Test1", "Testov1", Collections.emptyList());
    }

    public static List<Purchase> getTestPurchases() {
        return List.of(new Purchase(new User("Test", "Testov", Collections.emptyList()), PurchaseCategory.FUN, "test1", 0d),
                new Purchase(getTestUser(), PurchaseCategory.CLOTHES, "test2", 1d),
                new Purchase(getTestUser(), PurchaseCategory.FUN, "test3", 2d),
                new Purchase(getTestUser(), PurchaseCategory.MUN_SERVICES, "test3", 2d),
                new Purchase(getTestUser(), PurchaseCategory.MUN_SERVICES, "test3", 2d),
                new Purchase(new User("Test5", "Testov5", Collections.emptyList()), PurchaseCategory.FOOD, "test4", 3d),
                new Purchase(new User("Test6", "Testov6", Collections.emptyList()), PurchaseCategory.FUN, "test5", 4d));

    }
}
