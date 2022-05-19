package ru.prevent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "content")
    String content;

    @Column(name = "weight_arg")
    Long weight;

    @ManyToOne
    @JoinColumn(name = "id_question", nullable = false)
    QuestionEntity question;

    @OneToMany(mappedBy = "answer", fetch = FetchType.LAZY)
    @JsonIgnore
    List<UserAndAnswersEntity> userAnswers = new ArrayList<>();
}
