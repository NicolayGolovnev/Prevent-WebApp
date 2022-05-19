package ru.prevent.model;

import lombok.*;
import ru.prevent.entity.AnswerEntity;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class QuestionAnswersModel {
    Long id;
    String content;
    Long numQuestion;
    Long weight;
    List<AnswerEntity> answers;
    AnswerEntity userAnswer;
}
