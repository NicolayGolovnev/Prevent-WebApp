package ru.prevent.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.prevent.entity.Answer;
import ru.prevent.repository.AnswerRepository;

import java.util.Optional;

@Component
public class AnswerConverter implements Converter<String, Answer> {
    @Autowired
    AnswerRepository repository;

    @Override
    public Answer convert(String id) {
        Long idAnswer = Long.parseLong(id);

        Optional<Answer> answer = repository.findById(idAnswer);
        return answer.orElse(new Answer());
    }
}
