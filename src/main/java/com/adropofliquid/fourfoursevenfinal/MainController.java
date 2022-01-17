package com.adropofliquid.fourfoursevenfinal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {


    @Autowired
    RequestService requestService;

    @GetMapping("/")
    public String home(){

        return "index";
    }

    @GetMapping("/index")
    public String index(){
        return "redirect:/";
    }

    @GetMapping("/request")
    public String request(Model model){

        model.addAttribute("request", new Request());
        model.addAttribute("captcha", new Captcha().newCaptcha());
        return "request";
    }

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public String addRequest(@ModelAttribute Request request, RedirectAttributes attributes){

        String error = "";

        if(request.getCaptcha().equals(request.getCaptchaInput()))
            error = requestService.makeRequest(request);
        else
            error = Errors.CAPTCHA;


        if (error.length() > 5)
            attributes.addFlashAttribute("errorExist", 1);
        else
            attributes.addFlashAttribute("errorExist", 0);
        attributes.addFlashAttribute("error", error);
        attributes.addFlashAttribute("errorType", new Errors());

        return "redirect:/request";
    }

    @GetMapping("/requests")
    public String allRequests(Model model){
        model.addAttribute("requests", requestService.getAll());
        return "view";
    }


}