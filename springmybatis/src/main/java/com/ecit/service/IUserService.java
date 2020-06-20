package com.ecit.service;

import com.ecit.domain.User;

import java.util.List;

public interface IUserService {

    User save(User user);

    User save2(User user);

    List<User> queryUser(User user);

    int delete(int id);

    User get(int id);

    User get(User user);
}
