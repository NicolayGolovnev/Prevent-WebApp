package ru.prevent.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_n_quiz")
public class UserQuizzes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usr", nullable = false)
    User user;
}
