package ru.prevent.model;

import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ShowCompleteTestModel {
    Map<String, String> questionsAndAnswers;
    //private List<String> questions;
    //private List<String> answers;
    private String title;
    private String result;
    private String dateOfFinish;
}
