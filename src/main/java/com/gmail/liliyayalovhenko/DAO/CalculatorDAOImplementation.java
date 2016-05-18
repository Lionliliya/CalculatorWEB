package com.gmail.liliyayalovhenko.DAO;

import com.gmail.liliyayalovhenko.Enteties.Calculator;
import com.gmail.liliyayalovhenko.Enteties.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CalculatorDAOImplementation implements CalculatorDAO {

    @Autowired
    private EntityManager entityManager;


    @Override
    public void registerUser(User newUser) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newUser);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public User getUserByLogin(String username) {
        Query query = entityManager.createQuery("SELECT a FROM User a WHERE a.username =:var", User.class);
        query.setParameter("var", username);
        return (User)query.getResultList().get(0);
    }

    @Override
    public List<User> getAllUser() {
        Query query = entityManager.createQuery("SELECT a FROM User a", User.class);
        return (List<User>)query.getResultList();
    }

    @Override
    public List<Calculator> getAllExpOfUser(long idOfUser) {
        Query query = entityManager.createQuery("SELECT a FROM User a WHERE a.id =:var", User.class);
        query.setParameter("var", idOfUser);
        User user = (User)query.getResultList().get(0);
        return user.getAllExprOfUser();
    }

    @Override
    public List<Calculator> getAllExprWithError() {
        Query query = entityManager.createQuery("SELECT a FROM Calculator a where a.noError =:var", Calculator.class);
        query.setParameter("var", "no");
        return (List<Calculator>)query.getResultList();
    }

    @Override
    public List<Calculator> getAllExpression() {
        Query query = entityManager.createQuery("SELECT a FROM Calculator a", Calculator.class);
        return (List<Calculator>)query.getResultList();
    }

    @Override
    public void saveExpression(String expression, double result, String isError) {
        boolean noError = isError.equals("no");
        Calculator newExpression = new Calculator(expression, result, noError);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newExpression);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void saveExpression(Calculator calculator) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(calculator);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }


}
