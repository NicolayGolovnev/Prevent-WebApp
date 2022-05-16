package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
