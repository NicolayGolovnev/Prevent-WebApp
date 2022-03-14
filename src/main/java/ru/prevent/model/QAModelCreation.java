package ru.prevent.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class QAModelCreation {
    List<QuestionAnswersModel> questions = new ArrayList<>();

    public void add(QuestionAnswersModel question) {
        questions.add(question);
    }
}
