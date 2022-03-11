package ru.prevent.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "user_answer")

public class UserAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "content_answer")
    String contentAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_answer_n_question", nullable = false)
    QuestionAndAnswers questionAndAnswers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_question", nullable = false)
    Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_n_quiz", nullable = false)
    UserQuizzes userQuizzes;
}
