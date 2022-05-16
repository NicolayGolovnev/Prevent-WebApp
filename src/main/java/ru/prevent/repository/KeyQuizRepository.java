package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.KeyQuizEntity;

public interface KeyQuizRepository extends JpaRepository<KeyQuizEntity, Long> {
}
