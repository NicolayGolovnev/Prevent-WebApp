package ru.prevent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user_n_quiz")
public class UserAndQuizzesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "status")
    String status;

    @Column(name = "complete_date")
    LocalDate completeDate;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "id_usr", nullable = false)
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_quiz", nullable = false)
    QuizEntity quiz;

    @OneToMany(mappedBy = "userQuiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<UserAndAnswersEntity> userAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "userQuiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<HistoryResultsEntity> results = new ArrayList<>();

    public static boolean isCompatible(QuizEntity quiz, UserEntity user) {
        // проверяем пол пользователя и опроса
        if (!Objects.equals(user.getSex(), quiz.getGender()) && !quiz.getGender().equals("Любой"))
            return false;

        // проверяем возраст пользователя
        if (user.getAge() < quiz.getMinAge() || user.getAge() > quiz.getMaxAge())
            return false;

        return true;
    }
}
