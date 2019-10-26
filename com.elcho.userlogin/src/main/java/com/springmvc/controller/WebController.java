package com.springmvc.controller;  
  
  
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;  
  
@Controller  
public class WebController {  
    
    @RequestMapping(value="/")  
    public String Home(Model model){  
        model.addAttribute("message","index");  
        return "home";  
    }  
      
}  