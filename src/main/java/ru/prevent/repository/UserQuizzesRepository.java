package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.UserQuizzes;

public interface UserQuizzesRepository extends JpaRepository<UserQuizzes, Long> {
}
