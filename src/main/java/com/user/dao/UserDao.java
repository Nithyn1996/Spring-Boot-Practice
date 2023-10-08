package com.user.dao;

import com.user.User;
import com.user.constants.Constants;

import java.util.List;

public abstract class UserDao extends Constants{

    public abstract List<User> getAllUsers();

    public abstract User addUser(User user);

    public abstract int updateUser(User user);

    public abstract int deleteUser(int id);
}
