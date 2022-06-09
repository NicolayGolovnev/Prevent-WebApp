package ru.prevent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ModelAndView responseEntityException(Exception exception) {
//        log.error("[ExceptionHandler]\tThrowed some exception - return ResponseEntity with HttpStatus INTERNAL_SERVER_ERROR: " + exception);
        //TODO сделать отправку сообщения (лога) об ошибке на почту
        ModelAndView model = new ModelAndView("error");
        model.addObject("exception", exception);
        return model;
    }
}
