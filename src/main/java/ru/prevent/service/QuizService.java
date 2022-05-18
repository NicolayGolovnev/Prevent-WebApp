package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.*;
import ru.prevent.exception.QuizNotFoundException;
import ru.prevent.repository.QuizRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository repository;

    @Autowired
    UserService userService;

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
            throw new QuizNotFoundException("Quiz[id=" + id + "] not found!");
    }

    public QuizEntity findByTitle(String title) {
        Optional<QuizEntity> optional = repository.findByTitle(title);
        if (optional.isPresent())
            return optional.get();
        else
            throw new QuizNotFoundException("Quiz[title=" + title + "] not found!");
    }

    public void save(QuizEntity quiz) {
        // устанавливаем двустороннюю связь
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

        // если опросник открытый - сразу даем всем пользователям к нему доступ
        if (quiz.isAccess()) {
            List<UserEntity> users = userService.findAll();
            for (UserEntity user : users)
                quiz.getUsers().add(UserAndQuizzesEntity.builder()
                        .status("открытый")
                        .user(user)
                        .quiz(quiz)
                        .build());
        }


        repository.save(quiz);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
