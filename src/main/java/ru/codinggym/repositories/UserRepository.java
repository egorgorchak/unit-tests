package ru.codinggym.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.codinggym.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
