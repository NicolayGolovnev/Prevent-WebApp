package ru.prevent.model;

import lombok.*;
import ru.prevent.entity.Answer;

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
    List<Answer> answers;
    Answer userAnswer;
}
