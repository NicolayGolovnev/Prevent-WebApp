package ru.prevent.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "history_results")
public class HistoryResultsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "result")
    String result;

    @ManyToOne
    @JoinColumn(name = "id_user_n_quiz", nullable = false)
    UserAndQuizzesEntity userQuiz;

    @ManyToOne
    @JoinColumn(name = "id_usr", nullable = false)
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_children_quiz")
    QuizEntity childrenQuiz;

    public boolean checkChildrenQuiz(){
        return childrenQuiz != null;
    }
}
