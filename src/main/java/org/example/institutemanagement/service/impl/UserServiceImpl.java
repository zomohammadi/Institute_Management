package org.example.institutemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.institutemanagement.entity.Person;
import org.example.institutemanagement.entity.User;
import org.example.institutemanagement.repository.UserRepository;
import org.example.institutemanagement.service.UserService;
import org.example.institutemanagement.util.GenerateCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(Person person, String username) {
        return User.builder()
                .person(person)
                .username(username)
                .password(GenerateCode.generateSecurePassword())
                .build();
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }


}
