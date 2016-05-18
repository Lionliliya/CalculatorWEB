package com.gmail.liliyayalovhenko.Controller;

import com.gmail.liliyayalovhenko.DAO.CalculatorDAO;
import com.gmail.liliyayalovhenko.Enteties.Calculator;
import com.gmail.liliyayalovhenko.Enteties.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        return modelAndView;
    }

    @RequestMapping("/{id}")
    public ModelAndView mainUser(@PathVariable("id") long id,
                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session, id)) {
            modelAndView.setViewName("indexUser");
            modelAndView.addObject("user", calculatorDAO.getUserById(id));
        }
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public ModelAndView calculating(@RequestParam(value = "expression") String expression,
                                    HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Calculator calculator = new Calculator();
        calculator.setExpression(expression);
        calculator.calculateExpression();
        modelAndView.addObject("expression", expression);
        modelAndView.addObject("result", calculator);
        modelAndView.setViewName("index");
        calculatorDAO.saveCalculator(calculator);
        return modelAndView;
    }

    @RequestMapping(value = "/calculate/{id}", method = RequestMethod.POST)
    public ModelAndView calculatingWithLogin(@PathVariable("id") long id,
                                             @RequestParam(value = "expression") String expression,
                                             HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        Calculator calculator = new Calculator();
        calculator.setExpression(expression);
        calculator.calculateExpression();
        double result = calculator.getResult();
        modelAndView.addObject("expression", expression);
        modelAndView.addObject("result", result);
        if (checkStatus(session, id)) {
            User user = calculatorDAO.getUserById(id);
            calculator.setUser(user);
            calculatorDAO.saveCalculator(calculator);
            user.addExpression(calculator);
            modelAndView.setViewName("indexUser");
            modelAndView.addObject("user", user);

        } else {
            calculatorDAO.saveCalculator(calculator);
            modelAndView.setViewName("index");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "username") String username,
                              @RequestParam(value = "password") String password,
                              HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        User user = calculatorDAO.getUserByLoginAndPassword(username, password);

        if (user != null) {
            session.setAttribute("userId", user.getId());
            modelAndView.setViewName("indexUser");
            modelAndView.addObject("user", user);

        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("result", "You are not a member. Register, please.");
        }
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/register",  method = RequestMethod.POST)
    public ModelAndView register(@RequestParam(value = "username") String username,
                                 @RequestParam(value = "password") String password,
                                 HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        User user = new User(username, password);
        if (calculatorDAO.getUserByLoginAndPassword(username, password) == null) {
            modelAndView.addObject("result", "Now you can sign in");
            calculatorDAO.registerUser(user);
        } else {
            modelAndView.addObject("result", "User with such username and password already exist!");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/logout/{id}")
    public ModelAndView logout(@PathVariable("id") long id,
                               HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (checkStatus(session,id)) {
            session.removeAttribute("userId");
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

    public boolean checkStatus(HttpSession session, long user_id){
        boolean checking;
        long user_indent = (long)session.getAttribute("userId");
        checking = user_indent == user_id ? true : false;
        return checking;
    }

}
