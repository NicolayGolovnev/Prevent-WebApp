package ru.prevent.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_n_quiz")
public class UserQuizzes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "status")
    String status;

    @Column(name = "complete_date")
    Timestamp completeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usr", nullable = false)
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_quiz", nullable = false)
    Quiz quiz;

    @OneToMany(mappedBy = "userQuizzes", cascade = CascadeType.ALL, orphanRemoval = true)
    List<UserAnswers> userAnswers = new ArrayList<>();
}
