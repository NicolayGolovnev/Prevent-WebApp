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
@Table(name = "answer_n_question")

public class QuestionAndAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_question", nullable = false)
    Question question;

    // fetch = FetchType.LAZY
    @ManyToOne()
    @JoinColumn(name = "id_answer", nullable = false)
    Answer answer;

    @OneToMany(mappedBy = "questionAndAnswers", fetch = FetchType.LAZY)
    List<UserAnswers> userAnswers = new ArrayList<>();
}
