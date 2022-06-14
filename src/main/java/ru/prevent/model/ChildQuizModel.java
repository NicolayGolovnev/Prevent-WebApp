package ru.prevent.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChildQuizModel {
    private Long id;
    private List<QuestionAnswersModel> questions = new ArrayList<>();
    private String tittle;
    private Long weight;

    public void add(QuestionAnswersModel question) {
        questions.add(question);
    }

    public boolean checkTitle(){return !Objects.equals(tittle, "");}
}
