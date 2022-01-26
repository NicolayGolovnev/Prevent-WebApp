package ru.prevent.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "access")
    boolean access;

    @Column(name = "gender")
    String gender;

    @Column(name = "min_age")
    Long minAge;

    @Column(name = "max_age")
    Long maxAge;

    @Column(name = "weight_arg")
    Long weight;
}
