package ru.prevent.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByFirstNameAndLastNameAndThirdName(String firstName, String lastName, String thirdName);
    Optional<UserEntity> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<UserEntity> findByTelephone(String telephone);
}
