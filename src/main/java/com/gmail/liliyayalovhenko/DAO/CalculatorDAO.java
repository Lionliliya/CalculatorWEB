package com.gmail.liliyayalovhenko.DAO;

import com.gmail.liliyayalovhenko.Enteties.Calculator;
import com.gmail.liliyayalovhenko.Enteties.User;

import java.util.List;

public interface CalculatorDAO {

    public void registerUser(User newUser);

    public User getUserByLoginAndPassword(String username, String password);

    public List<User> getAllUser();

    public List<Calculator> getAllExpOfUser(long idOfUser);

    public  List<Calculator> getAllExprWithError();

    public List<Calculator> getAllExpression();

    public void saveExpression(String expression, double result, boolean isError);

    public void saveCalculator(Calculator calculator);

    public User getUserById(long id);

}
