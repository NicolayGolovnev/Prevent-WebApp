package ru.prevent.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_n_quiz")
public class UserQuizzes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "status")
    String status;

    @Column(name = "complete_date")
    LocalDate completeDate;

    @Column(name = "result")
    String result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usr", nullable = false)
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_quiz", nullable = false)
    Quiz quiz;

    @OneToMany(mappedBy = "userQuizzes", cascade = CascadeType.ALL, orphanRemoval = true)
    List<UserAnswers> userAnswers = new ArrayList<>();
}
