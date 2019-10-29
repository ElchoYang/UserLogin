package com.ibm.mvc.controller;

import com.ibm.mvc.entity.User;
import com.ibm.mvc.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class UserController {


    private static final Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private IUserService UserService;

    @RequestMapping(value = "/")
    public String Home(Model model) throws Exception {
        List<User> userList = UserService.findAll();
        log.info("Index");
        log.info(userList.get(0).getUserName());
        return "index";
    }


    @RequestMapping(value = "/user/admin")
    public String Admin(Model model) throws Exception {
        log.info("Admin");
        return "admin";
    }

    @RequestMapping(value = "/user/tutorials")
    public String tutorials(Model model) throws Exception {
        log.info("Tutorials");
        return "tutorials";
    }

    @RequestMapping(value = "/user/update")
    public String update(Model model) throws Exception {
        log.info("update");
        return "update";
    }

    @RequestMapping(value = "/user/logon")
    public String Logon(User u, Model model) throws Exception {

        log.info("--- Logon user: " + u.getUserName());

        User username = null;

        if(u.getUserName()!=null) {
            username = UserService.findUserByUserName(u.getUserName());
            if(username == null) {
                log.info("user not exist");
                model.addAttribute("error", "User Not exist");
            } else {
                if (!username.getPassword().equals(u.getPassword())) {
                    System.out.println("Password is not correct");
                    model.addAttribute("error", "password not correct");
                } else {
                    return "update";
                }

            }
        }


        return "logon";
    }

    @RequestMapping(value = "/user/regist")
    public String Regist(User u, Model model) throws Exception {

        return "regist";
    }


}  