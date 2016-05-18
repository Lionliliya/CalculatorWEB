package com.gmail.liliyayalovhenko.DAO;

import com.gmail.liliyayalovhenko.Enteties.Calculator;
import com.gmail.liliyayalovhenko.Enteties.User;

import java.util.List;

public interface CalculatorDAO {

    public void registerUser(User newUser);

    public User getUserByLogin(String username);

    public List<User> getAllUser();

    public List<Calculator> getAllExpOfUser(long idOfUser);

    public  List<Calculator> getAllExprWithError();

    public List<Calculator> getAllExpression();

    public void saveExpression(String expression, double result, String isError);

    public void saveExpression(Calculator calculator);

}
