package com.ibm.mvc.service;

import java.util.List;

import com.ibm.mvc.entity.User;

public interface IUserService {

    void add(User u);

    User findUserById(Integer id);

    User findUserByUserName(String username);
    
    List<User> findAll();

    void update(User u, Integer id);

    void delete(Integer id);
    
}
