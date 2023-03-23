package com.aanshik.UserService.Services;

import com.aanshik.UserService.Entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    User getUser(String userId);

    //TODO: Delete
    //TODO: Update
}
