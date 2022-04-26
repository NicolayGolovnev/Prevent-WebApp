package ru.prevent.entity;

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
@Table(name = "question")

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "content")
    String content;

    @Column(name = "num_question")
    Long numQuestion;

    @Column(name = "weight_arg")
    Long weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_quiz", nullable = false)
    Quiz quiz;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    List<UserAnswers> userAnswers = new ArrayList<>();
}

