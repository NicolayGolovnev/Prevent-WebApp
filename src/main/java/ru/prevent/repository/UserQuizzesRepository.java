package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.UserAndQuizzesEntity;

public interface UserQuizzesRepository extends JpaRepository<UserAndQuizzesEntity, Long> {
}
