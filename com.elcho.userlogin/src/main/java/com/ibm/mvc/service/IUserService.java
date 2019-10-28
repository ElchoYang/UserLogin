package com.ibm.mvc.service;

import java.util.List;

import com.ibm.mvc.entity.User;

public interface IUserService {

    void add(User u);

    User findUserById(String id);

    User findUserByUserName(String username);
    
    List<User> findAll();

    void update(User u);

    void delete(Integer id);
    
}
