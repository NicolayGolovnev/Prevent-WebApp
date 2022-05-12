package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.QuizAndQuizEntity;

import java.util.List;

public interface QuizAndQuizRepository extends JpaRepository<QuizAndQuizEntity, Long> {

    public List<QuizAndQuizEntity> findAllByParentQuiz_Id(Long quizId);
}
