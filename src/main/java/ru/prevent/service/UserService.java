package ru.prevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prevent.entity.UserEntity;
import ru.prevent.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    public UserEntity findById(Long id) {
        Optional<UserEntity> optionalUser = repository.findById(id);
        if (optionalUser.isPresent())
            return optionalUser.get();
        else
            throw new RuntimeException("User with id=" + id + " not found!");
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
