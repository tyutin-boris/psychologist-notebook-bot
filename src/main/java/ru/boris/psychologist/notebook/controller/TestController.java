package ru.boris.psychologist.notebook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.boris.psychologist.notebook.model.entity.UserEntity;
import ru.boris.psychologist.notebook.model.repository.UserRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class TestController {

    private final UserRepository repository;

    @GetMapping
    public List<UserEntity> findAll() {
        return repository.findAll();
    }
}
