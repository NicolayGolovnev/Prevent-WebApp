package ru.prevent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user_n_quiz")
public class UserAndQuizzesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "status")
    String status;

    @Column(name = "complete_date")
    LocalDate completeDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_usr", nullable = false)
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_quiz", nullable = false)
    QuizEntity quiz;

    @OneToMany(mappedBy = "userQuizzes", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<UserAndAnswersEntity> userAnswers = new ArrayList<>();
}
