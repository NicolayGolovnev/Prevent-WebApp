package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.KeyQuizEntity;

import java.util.List;

public interface KeyQuizRepository extends JpaRepository<KeyQuizEntity, Long> {

    public List<KeyQuizEntity> findAllByQuiz_Id(Long id);
}
