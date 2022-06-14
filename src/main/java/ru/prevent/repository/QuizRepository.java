package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.QuizEntity;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
    List<QuizEntity> findAllByAccessIsTrue();

    List<QuizEntity> findAllByAccessIsFalse();

    Optional<QuizEntity> findByTitle(String title);
}
