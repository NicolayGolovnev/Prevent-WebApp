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
public class UserAndAnswersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "content_answer")
    String contentAnswer;

    @ManyToOne
    @JoinColumn(name = "id_user_n_quiz", nullable = false)
    UserAndQuizzesEntity userQuiz;

    @ManyToOne
    @JoinColumn(name = "id_answer", nullable = false)
    AnswerEntity answer;

    @ManyToOne
    @JoinColumn(name = "id_question", nullable = false)
    QuestionEntity question;

    @ManyToOne
    @JoinColumn(name = "id_usr", nullable = false)
    UserEntity user;
}
