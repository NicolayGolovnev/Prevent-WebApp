package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.UserQuizzesEntity;

public interface UserQuizzesRepository extends JpaRepository<UserQuizzesEntity, Long> {
}
