package com.joshrincon.springyummly.controller;

import com.joshrincon.springyummly.dao.User;
import com.joshrincon.springyummly.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {
    private UsersService usersService;

    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping("/loggedout")
    public String showLoggedOut() {
        return "loggedout";
    }

    @RequestMapping("/register")
    public String showNewAccount(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping("/admin")
    //argument takes type ModelMap
    public String showAdmin(Model model) {
        List<User> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping("/denied")
    public String showDenied() {
        return "denied";
    }

    @RequestMapping(value="/createaccount", method= RequestMethod.POST)
    public String createAccount(@Valid User user, BindingResult result) {
        //TODO: check if passwords matched
        // do some validation
        if(result.hasErrors()) {
            return "register";
        }
        user.setEnabled(true);
        user.setAuthority("ROLE_USER");
        //TODO: check if email exists
        if(usersService.exists(user.getUsername())){
            result.rejectValue("username", "DuplicateKey.user.username");
            return "register";
        }
        try {
            // create the offer if no duplicate username is there (the catch is a fallback if the first if statement fails)
            usersService.create(user);
        } catch (DuplicateKeyException e) {
            result.rejectValue("username", "DuplicateKey.user.username");
            return "register";
        }
        return "accountcreated";
    }
}