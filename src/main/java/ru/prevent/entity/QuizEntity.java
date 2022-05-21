package ru.prevent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "quiz")
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "access")
    boolean access;

    @Column(name = "gender")
    String gender;

    @Column(name = "min_age")
    Long minAge;

    @Column(name = "max_age")
    Long maxAge;

    @Column(name = "weight_arg")
    Long weight = 1L;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<UserAndQuizzesEntity> users = new ArrayList<>();

    @OneToMany(mappedBy = "childrenQuiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<HistoryResultsEntity> results = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<KeyQuizEntity> keys = new ArrayList<>();

    @OneToMany(mappedBy = "childQuiz")
    @JsonIgnore
    List<QuizAndQuizEntity> parentQuizzes = new ArrayList<>();

    @OneToMany(mappedBy = "parentQuiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<QuizAndQuizEntity> childQuizzes = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<QuestionEntity> questions = new ArrayList<>();
}
