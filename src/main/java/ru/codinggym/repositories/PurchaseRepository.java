package ru.codinggym.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.codinggym.model.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

}
