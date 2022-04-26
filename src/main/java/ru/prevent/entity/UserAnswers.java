package ru.prevent.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "id_user_n_quiz", nullable = false)
    UserQuizzes userQuizzes;

    @ManyToOne
    @JoinColumn(name = "id_answer", nullable = false)
    Answer answer;

    @ManyToOne
    @JoinColumn(name = "id_question", nullable = false)
    Question question;
}
