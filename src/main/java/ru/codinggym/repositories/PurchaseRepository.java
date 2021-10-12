package ru.codinggym.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.codinggym.model.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    @Query(value = "SELECT SUM(purchase_sum) from purchase where user_id = ?1", nativeQuery = true)
    Double getPurchaseSumByUserId(Long userId);
}
