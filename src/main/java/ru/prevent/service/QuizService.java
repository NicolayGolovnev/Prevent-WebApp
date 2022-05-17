package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.*;
import ru.prevent.repository.QuizRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository repository;

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    public List<QuizEntity> findAll() {
        return repository.findAll();
    }
    public List<QuizEntity> findAllByAccessIsTrue() {
        return repository.findAllByAccessIsTrue();
    }

    public QuizEntity findById(Long id) {
        Optional<QuizEntity> optional = repository.findById(id);
        if (optional.isPresent())
            return optional.get();
        else
            throw new RuntimeException("Quiz with id=" + id + " not found!");
    }

    public void save(QuizEntity quiz) {
        if (!quiz.getChildQuizzes().isEmpty())
            for (QuizAndQuizEntity child : quiz.getChildQuizzes())
                child.setParentQuiz(quiz);
        if (!quiz.getKeys().isEmpty())
            for (KeyQuizEntity key : quiz.getKeys())
                key.setQuiz(quiz);
        if (!quiz.getQuestions().isEmpty())
            for (QuestionEntity question : quiz.getQuestions()) {
                question.setQuiz(quiz);
                if (!question.getAnswers().isEmpty())
                    for (AnswerEntity answer : question.getAnswers())
                        answer.setQuestion(question);
            }

        repository.save(quiz);
//        questionService.saveAll(quiz.getQuestions());
//        for (QuestionEntity question : quiz.getQuestions())
//            answerService.saveAll(question.getAnswers());
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
