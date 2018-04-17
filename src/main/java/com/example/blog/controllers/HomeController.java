package com.example.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    //    Listen for this URL pattern
    @GetMapping("/home")
    public String welcome(){
        return "home";
    }

//    @GetMapping("/logout")
//    @ResponseBody
//    public String logout(){
//        return "You logged out";
//    }
//
//    @RequestMapping(path = "/lights", method = RequestMethod.GET)
//    @ResponseBody
//    public String lights() {
//        return "Lights ON";
//    }
//
    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}
