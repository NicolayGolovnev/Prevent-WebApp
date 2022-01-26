package ru.prevent.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

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
    List<UserQuizzes> users = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    List<keyQuiz> keys = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    List<Question> questions = new ArrayList<>();
}
