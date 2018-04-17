package com.example.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiceController {
    @GetMapping("/roll-dice")
    public String rollDice(){
        return ("dice");
    }

    @GetMapping("/roll-dice/{pick}")
    public String rollDice(Model model, @PathVariable int pick){
        //generate randNum for number of dice
        model.addAttribute("pick", pick);
        int num = (int) Math.floor(Math.random() * 6) + 1;
        model.addAttribute("num", num);
        return ("dice");
    }
}
