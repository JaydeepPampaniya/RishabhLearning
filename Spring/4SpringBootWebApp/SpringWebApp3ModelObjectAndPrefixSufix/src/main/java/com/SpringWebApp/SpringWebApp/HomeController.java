package com.SpringWebApp.SpringWebApp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping("/")
    public ModelAndView home(ModelAndView mv){
         mv.setViewName("index");
         return mv;
    }

//    @RequestMapping("add")
//    public String add(@RequestParam("num1") int num, int num2, Model model){
//
//        int result = num+num2;
//        model.addAttribute("result",result);
//        return "result.jsp";
//    }
    @RequestMapping("add")
    public ModelAndView modelAndView(@RequestParam("num1") int num1,@RequestParam("num2") int number, ModelAndView mv){
        int result  = num1 + number;
        mv.addObject("result",result);
        mv.setViewName("result");
        return mv;
    }
}
