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

    @Column(name = "title")
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
    Long weight;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    @JsonIgnore
    List<UserQuizzesEntity> users = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<KeyQuizEntity> keys = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    @JsonIgnore
    List<QuestionEntity> questions = new ArrayList<>();

    @OneToMany(mappedBy = "childQuiz", fetch = FetchType.LAZY)
    @JsonIgnore
    List<QuizAndQuiz> parents = new ArrayList<>();

    @OneToMany(mappedBy = "parentQuiz", fetch = FetchType.LAZY)
    @JsonIgnore
    List<QuizAndQuiz> childrens = new ArrayList<>();
}
