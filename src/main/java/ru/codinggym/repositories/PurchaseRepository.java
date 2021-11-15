package ru.codinggym.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.codinggym.model.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    @Query(value = "SELECT SUM(purchase_sum) FROM purchase JOIN person ON purchase.user_id = person.user_id WHERE last_name = ?1", nativeQuery = true)
    Double getPurchaseSumByUserLastName(String lastName);
}
