package ru.prevent.service;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.prevent.entity.QuizEntity;
import ru.prevent.entity.UserAndQuizzesEntity;
import ru.prevent.entity.UserEntity;
import ru.prevent.exception.ObjectNotFoundException;
import ru.prevent.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private QuizService quizService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public UserEntity findById(Long id) {
        Optional<UserEntity> optionalUser = repository.findById(id);
        if (optionalUser.isPresent())
            return optionalUser.get();
        else
            throw new ObjectNotFoundException("User[id=" + id + "] not found!");
    }

    @Transactional(readOnly = true)
    public UserEntity findByUsername(String username) {
        Optional<UserEntity> optionalUser = repository.findByTelephone(username);
        if (optionalUser.isPresent())
            return optionalUser.get();
        else
            throw new ObjectNotFoundException("User[telephone=" + username + "] not found!");
    }

    @Transactional
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

            user.setPassword(RandomStringUtils.randomAlphanumeric(6, 12));
            emailSenderService.sendCredentials(user);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        } else {
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

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = repository.findByTelephone(username);
        return userEntity.orElseThrow(() -> new ObjectNotFoundException("User [telephone=" + username + "] not found!"));
    }

    @Transactional
    public void register(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repository.save(user);
    }
}
