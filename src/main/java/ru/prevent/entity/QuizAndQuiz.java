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
public class QuizAndQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parent_quiz", nullable = false)
    Quiz parentQuiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_child_quiz", nullable = false)
    Quiz childQuiz;
}
