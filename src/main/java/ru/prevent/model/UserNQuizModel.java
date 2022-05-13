package ru.prevent.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserNQuizModel {
    Long userId;
    Long quizId;
}
