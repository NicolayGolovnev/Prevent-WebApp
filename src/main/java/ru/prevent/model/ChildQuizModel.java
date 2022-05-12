package ru.prevent.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChildQuizModel {
    private List<QuestionAnswersModel> questions = new ArrayList<>();
    private String tittle;
    public void add(QuestionAnswersModel question) {
        questions.add(question);
    }
}
