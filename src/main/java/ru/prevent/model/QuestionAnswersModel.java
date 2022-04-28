package ru.prevent.model;

import lombok.*;
import ru.prevent.entity.AnswerEntity;
import ru.prevent.entity.QuestionEntity;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class QuestionAnswersModel {
    Long id;
    /*String content;
    Long numQuestion;
    Long weight;*/
    QuestionEntity question;
    List<AnswerEntity> answers;
    AnswerEntity userAnswer;
}
