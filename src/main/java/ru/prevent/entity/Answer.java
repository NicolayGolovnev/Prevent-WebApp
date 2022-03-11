package ru.prevent.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "answer")

public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "content")
    String content;

    @Column(name = "weight_arg")
    Long weight;

    @OneToMany(mappedBy = "answer", fetch = FetchType.LAZY)
    List<QuestionAndAnswers> questions = new ArrayList<>();

}
