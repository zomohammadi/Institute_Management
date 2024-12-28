package org.example.institutemanagement.service;

import org.example.institutemanagement.entity.Person;
import org.example.institutemanagement.entity.User;

public interface UserService {
    User createUser(Person person, String username);

    boolean existsByUsername(String username);

    void save(User user);
}
