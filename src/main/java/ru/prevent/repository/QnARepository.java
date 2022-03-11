package ru.prevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.prevent.entity.QuestionAndAnswers;

import java.util.List;

public interface QnARepository extends JpaRepository<QuestionAndAnswers, Long> {
    List<QuestionAndAnswers> findAllByQuestion_NumQuestion(Long numQuestion);
}
