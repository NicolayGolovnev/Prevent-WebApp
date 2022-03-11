package ru.prevent.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "key_quiz")
public class keyQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "min_arg")
    Long minArg;

    @Column(name = "max_arg")
    Long maxArg;

    @Column(name = "result_arg")
    Long resultArg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_quiz", nullable = false)
    Quiz quiz;
}
