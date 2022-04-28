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
@Table(name = "question")
public class QuestionEntity {
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
    QuizEntity quiz;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @JsonIgnore
    List<AnswerEntity> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @JsonIgnore
    List<UserAndAnswersEntity> userAnswers = new ArrayList<>();
}

