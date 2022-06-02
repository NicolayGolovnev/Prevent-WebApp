package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.QuizEntity;
import ru.prevent.entity.UserAndQuizzesEntity;
import ru.prevent.entity.UserEntity;
import ru.prevent.exception.ObjectNotFoundException;
import ru.prevent.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    QuizService quizService;

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    public UserEntity findByFIO(String fio) {
        String[] names = fio.split(" ");
        Optional<UserEntity> optionalUser = Optional.empty();
        if (names.length == 2)
            optionalUser = repository.findByFirstNameAndLastName(names[0], names[1]);
        else if (names.length == 3)
            optionalUser = repository.findByFirstNameAndLastNameAndThirdName(names[0], names[1], names[2]);

        if (optionalUser.isPresent())
            return optionalUser.get();
        else
            throw new ObjectNotFoundException("User[firstName=" + names[0] + ", lastName=" + names[1] + "] not found!");
    }

    public UserEntity findById(Long id) {
        Optional<UserEntity> optionalUser = repository.findById(id);
        if (optionalUser.isPresent())
            return optionalUser.get();
        else
            throw new ObjectNotFoundException("User[id=" + id + "] not found!");
    }

    public void save(UserEntity user) {
        if (user.getId() == null) {
            List<QuizEntity> openQuizzes = quizService.findAllByAccessIsTrue();
            List<UserAndQuizzesEntity> userQuizzes = new ArrayList<>();
            for (QuizEntity quiz : openQuizzes)
                if (UserAndQuizzesEntity.isCompatible(quiz, user))
                    userQuizzes.add(UserAndQuizzesEntity.builder()
                            .status("открытый")
                            .user(user)
                            .quiz(quiz)
                            .build());
            user.setQuizzes(userQuizzes);
        }
        else {
            // update
            List<UserAndQuizzesEntity> checkQuizzes = new ArrayList<>();
            for (UserAndQuizzesEntity userQuiz : user.getQuizzes()) {
                if (userQuiz.getStatus().equals("назначен")) {
                    checkQuizzes.add(userQuiz);
                    continue;
                }

                if (UserAndQuizzesEntity.isCompatible(userQuiz.getQuiz(), user))
                    checkQuizzes.add(userQuiz);
            }

            user.setQuizzes(checkQuizzes);
        }

        repository.save(user);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
