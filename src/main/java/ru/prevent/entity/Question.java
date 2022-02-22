package ru.prevent.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
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

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    List<QuestionAndAnswers> answers = new ArrayList<>();
}

