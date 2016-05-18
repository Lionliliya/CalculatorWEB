package com.gmail.liliyayalovhenko.Controller;

import com.gmail.liliyayalovhenko.DAO.CalculatorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {
    @Autowired
    private CalculatorDAO calculatorDAO;

    @RequestMapping("/")
    public ModelAndView main(HttpServletRequest request) {
        HttpSession session = request.getSession();
        //checkSession(session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }



    private void checkSession(HttpSession session) {
    }
}
