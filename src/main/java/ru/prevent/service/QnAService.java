package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.Answer;
import ru.prevent.entity.Question;
import ru.prevent.entity.QuestionAndAnswers;
import ru.prevent.exception.AnswerNotFoundException;
import ru.prevent.exception.QuestionNotFoundException;
import ru.prevent.repository.AnswerRepository;
import ru.prevent.repository.QnARepository;
import ru.prevent.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QnAService {
    @Autowired
    QnARepository qNaRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    public List<QuestionAndAnswers> getAnswersByQuestion(Long numQuestion) {
        return qNaRepository.findAllByQuestion_NumQuestion(numQuestion);
    }

    public void addAnswerToQuestion(Long idAnswer, Long idQuestion) {
        Optional<Question> optionalQ = questionRepository.findById(idQuestion);
        if (optionalQ.isEmpty())
            throw new QuestionNotFoundException("Question[id = " + idQuestion + "] not found!");
        Question question = optionalQ.get();

        Optional<Answer> optionalA = answerRepository.findById(idAnswer);
        if (optionalA.isEmpty())
            throw new AnswerNotFoundException("Answer[id = " + idAnswer + "] not found!");
        Answer answer = optionalA.get();

        QuestionAndAnswers qNa = QuestionAndAnswers.builder()
                .question(question)
                .answer(answer)
                .build();
        qNaRepository.save(qNa);
    }
}
