package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.prevent.entity.*;
import ru.prevent.exception.ObjectNotFoundException;
import ru.prevent.repository.QuizRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepository repository;

    @Autowired
    private UserService userService;

    public List<QuizEntity> findAll() {
        return repository.findAll();
    }

    public List<QuizEntity> findAllClosed() {
        return repository.findAllByAccessIsFalse();
    }

    public List<QuizEntity> findAllByAccessIsTrue() {
        return repository.findAllByAccessIsTrue();
    }

    @Transactional(readOnly = true)
    public QuizEntity findById(Long id) {
        Optional<QuizEntity> optional = repository.findById(id);
        if (optional.isPresent())
            return optional.get();
        else
            throw new ObjectNotFoundException("Quiz[id=" + id + "] not found!");
    }

    @Transactional(readOnly = true)
    public QuizEntity findByTitle(String title) {
        Optional<QuizEntity> optional = repository.findByTitle(title);
        if (optional.isPresent())
            return optional.get();
        else
            throw new ObjectNotFoundException("Quiz[title=" + title + "] not found!");
    }

    @Transactional
    public void save(QuizEntity quiz) {
        if (quiz.getId() == null) {
            // устанавливаем двустороннюю связь
            if (!quiz.getChildQuizzes().isEmpty()) {
                // для начала проверяем все дочерние опросники (есть ли среди них пустые)
                List<QuizAndQuizEntity> childQuizzes = new ArrayList<>();
                for (QuizAndQuizEntity child : quiz.getChildQuizzes())
                    if (child != null) {
                        child.setParentQuiz(quiz);
                        childQuizzes.add(child);
                    }
                quiz.setChildQuizzes(childQuizzes);
            }
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
            quiz.setUsers(new ArrayList<>());
            if (quiz.isAccess()) {
                List<UserEntity> users = userService.findAll();
                for (UserEntity user : users) {
                    if (UserAndQuizzesEntity.isCompatible(quiz, user))
                        quiz.getUsers().add(UserAndQuizzesEntity.builder()
                                .status("открытый")
                                .user(user)
                                .quiz(quiz)
                                .build());
                }
            }
        }

        repository.save(quiz);
    }

    private void editUserAnswers(List<UserAndAnswersEntity> answers) {
        // для каждого ответа мы убираем ссылку на вопрос и ответ -
        // записываем их в html форме в поле развернутого ответа
        for (UserAndAnswersEntity answer : answers) {
            String contentAnswer = "Вопрос: " + answer.getQuestion().getContent() +
                    "<br>" + "Ответ: " + answer.getAnswer().getContent();
            answer.setContentAnswer(contentAnswer);
            answer.setQuestion(null);
            answer.setAnswer(null);
        }
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
