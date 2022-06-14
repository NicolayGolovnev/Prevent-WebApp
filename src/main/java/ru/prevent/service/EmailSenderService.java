package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.prevent.entity.UserEntity;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String username;

    @Transactional
    public void sendSimpleEmail(String emailTo, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setTo(emailTo);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);
    }

    @Transactional
    public void sendCredentials(UserEntity user) {
        String fio = "";
        if (user.getThirdName().isEmpty())
            fio = user.getFirstName() + " " + user.getLastName();
        else
            fio = user.getLastName() + " " + user.getThirdName();
        String message = "Здравствуйте, " + fio + "!\n" +
                "Вас успешно зарегистрировали в личном кабинете Центра Превент!\n\n" +
                "Ваши регистрационные данные:\n" +
                "Логин: " + user.getTelephone() + "\n" +
                "Пароль: " + user.getPassword() + "\n\n" +
                "С наилучшими пожеланиями,\n" +
                "Администрация Центра Превент\n" +
                "https://preventplus.ru/";

        sendSimpleEmail(user.getEmail(), "Регистрация в личном кабинете Prevent+", message);
    }
}
