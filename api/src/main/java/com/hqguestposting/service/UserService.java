package com.hqguestposting.service;

import com.hqguestposting.model.User;
import javax.naming.AuthenticationException;
import java.util.List;

public interface UserService {
    User create(User user);

    User findById(Long id);

    List<User> findAll();

    User login(String email, String password) throws AuthenticationException;

    User findByEmail(String email);
}
