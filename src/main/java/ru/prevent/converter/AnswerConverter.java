package ru.prevent.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.prevent.entity.AnswerEntity;
import ru.prevent.repository.AnswerRepository;

import java.util.Optional;

@Component
public class AnswerConverter implements Converter<String, AnswerEntity> {
    @Autowired
    AnswerRepository repository;

    @Override
    public AnswerEntity convert(String id) {
        Long idAnswer = Long.parseLong(id);

        Optional<AnswerEntity> answer = repository.findById(idAnswer);
        return answer.orElse(new AnswerEntity());
    }
}
