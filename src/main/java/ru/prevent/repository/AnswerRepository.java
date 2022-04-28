package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.AnswerEntity;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
}
