package com.ibm.mvc.controller;

import com.ibm.mvc.entity.User;
import com.ibm.mvc.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class UserController {


    private static final Logger log = Logger.getLogger(UserController.class);

    private static String str_user = "user";
    private static String SESSION_USER = "sessionUser";
    @Autowired
    private IUserService UserService;

    @RequestMapping(value = "/")
    public String Home(Model model, HttpServletRequest request) throws Exception {
        List<User> userList = UserService.findAll();
        log.info("Index");
        log.info(userList.get(0).getUserName());

        for (User uu : userList) {
            log.info("--------  Regist FindALl: -------------");
            log.info("--------  Regist ID: " + uu.getId());
            log.info("--------  Regist name: " + uu.getName());
            log.info("--------  Regist email: " + uu.getEmail());
            log.info("--------  Regist user name: " + uu.getUserName());
            log.info("--------  Regist password: " + uu.getPassword());
            log.info("--------  Regist role: " + uu.getRole());
        }
        request.getSession().removeAttribute(SESSION_USER);
        return "index";
    }


    @RequestMapping(value = "/user/admin")
    public String Admin(Model model, HttpServletRequest request) throws Exception {
        log.info("Admin");
        return "admin";
    }

    @RequestMapping(value = "/user/tutorials")
    public String tutorials(Model model, HttpServletRequest request) throws Exception {
        log.info("Tutorials");
        return "tutorials";
    }

    @RequestMapping(value = "/user/update")
    public String update(User u, Model model, HttpServletRequest request) throws Exception {
        log.info("update");
        if(u!=null && u.getUserName()!=null) {
            log.info("update UserName: " + u.getUserName());
            User sessionUser = (User) request.getSession().getAttribute(SESSION_USER);
             UserService.update(u, sessionUser.getId());

             sessionUser = UserService.findUserById(sessionUser.getId());
             request.getSession().setAttribute(SESSION_USER,sessionUser);

             model.addAttribute("info", "Update successful");
            /* return "forward:/user/logon";*/
        }
        return "update";
    }

    @RequestMapping(value = "/user/logon")
    public String Logon(User u, Model model, HttpServletRequest request) throws Exception {

        log.info("--- Logon user: " + u.getUserName());

        User dbUser = null;

        if(u.getUserName()!=null) {
            dbUser = UserService.findUserByUserName(u.getUserName());
            if(dbUser == null) {
                log.info("user not exist");
                model.addAttribute("error", "User Not exist");
            } else {
                if (!dbUser.getPassword().equals(u.getPassword())) {
                    System.out.println("Password is not correct");
                    model.addAttribute("error", "password not correct");
                } else {
                    log.info("request session user: " + dbUser.getUserName());
                    request.getSession().setAttribute(SESSION_USER, dbUser);
                }
            }
        }

        // model.addAttribute(str_user, dbUser);
        return "logon";
    }

    @RequestMapping(value = "/user/regist")
    public String Regist(User u, Model model, HttpServletRequest request) throws Exception {

        if(u!=null && u.getUserName()!=null) {
            log.info("Regist UserName: " + u.getUserName());
            User dbUser = UserService.findUserByUserName(u.getUserName());

            if(dbUser!=null) {
                model.addAttribute("error", "User Already exist");
                return "regist";
            }
            log.info("--------  Regist name: " + u.getName());
            log.info("--------  Regist Email: " + u.getEmail());
            log.info("--------  Regist user name: " + u.getUserName());
            log.info("--------  Regist password: " + u.getPassword());
            UserService.add(u);

            return "logon";
           /* return "forward:/user/logon";*/
        }


        return "regist";
    }


}  