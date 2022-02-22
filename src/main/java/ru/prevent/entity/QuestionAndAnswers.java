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
@Table(name = "answer_n_question")

public class QuestionAndAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_question", nullable = false)
    Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_answer", nullable = false)
    Answer answer;

    @OneToMany(mappedBy = "answer_n_question", fetch = FetchType.LAZY)
    List<UserAnswers> userAnswers = new ArrayList<>();
}
