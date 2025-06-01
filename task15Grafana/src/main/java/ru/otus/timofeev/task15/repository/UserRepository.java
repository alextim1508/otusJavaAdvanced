package ru.otus.timofeev.task15.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.timofeev.task15.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
