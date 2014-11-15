package com.joshrincon.springyummly.service;


import com.joshrincon.springyummly.dao.User;
import com.joshrincon.springyummly.dao.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("usersService")
public class UsersService {
    private UsersDAO usersDAO;
    @Autowired
    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public void create(User user) {
        usersDAO.create(user);
    }
    public boolean exists(String username) {
        return usersDAO.exists(username);
    }

    public List<User> getAllUsers() {
        return usersDAO.getAllUsers();
    }
}
