package ru.prevent.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class QuizModel {
    private List<ChildQuizModel> childQuizzes = new ArrayList<>();
    private Long userId = 0L;
    private Long quizId = 0L;

    public void add(ChildQuizModel childQuiz) {
        childQuizzes.add(childQuiz);
    }
}
