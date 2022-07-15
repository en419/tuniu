package com.tuniu.dao;

import com.tuniu.Pojo.User;

public interface userDao {
    void registeruser(User user);
    User checkusername(String username);
}
