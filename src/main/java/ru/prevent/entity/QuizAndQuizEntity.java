package ru.prevent.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "quiz_n_quiz")
public class QuizAndQuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_parent_quiz", nullable = false)
    QuizEntity parentQuiz;

    @ManyToOne
    @JoinColumn(name = "id_child_quiz", nullable = false)
    QuizEntity childQuiz;
}
