package com.gmail.liliyayalovhenko.DAO;

import com.gmail.liliyayalovhenko.Enteties.Calculator;
import com.gmail.liliyayalovhenko.Enteties.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CalculatorDAOImplementation implements CalculatorDAO {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private static Logger log;

    @Override
    public void registerUser(User newUser) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newUser);
            entityManager.getTransaction().commit();
            log.info("Persist new user " + newUser);
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            log.info("Transaction rollback. Method register(User)");
            log.error(ex);
            ex.printStackTrace();
        }
    }

    @Override
    public User getUserByLoginAndPassword(String username, String password) {
        Query query = entityManager.createQuery("SELECT a FROM User a WHERE a.username =:var1 and a.password =:var2", User.class);
        query.setParameter("var1", username);
        query.setParameter("var2", password);
        List<User> userList = (List<User>) query.getResultList();
        return userList.isEmpty() ? null : (User) query.getResultList().get(0);
    }

    @Override
    public List<User> getAllUser() {
        Query query = entityManager.createQuery("SELECT a FROM User a", User.class);
        return (List<User>) query.getResultList();
    }

    @Override
    public List<Calculator> getAllExpOfUser(long idOfUser) {
        Query query = entityManager.createQuery("SELECT a FROM User a WHERE a.id =:var", User.class);
        query.setParameter("var", idOfUser);
        User user = (User) query.getResultList().get(0);
        return user.getAllExprOfUser();
    }

    @Override
    public List<Calculator> getAllExprWithError() {
        Query query = entityManager.createQuery("SELECT a FROM Calculator a where a.noError =:var", Calculator.class);
        query.setParameter("var", "no");
        return (List<Calculator>) query.getResultList();
    }

    @Override
    public List<Calculator> getAllExpression() {
        Query query = entityManager.createQuery("SELECT a FROM Calculator a", Calculator.class);
        return (List<Calculator>) query.getResultList();
    }

    @Override
    public void saveExpression(String expression, double result, boolean isError) {
        Calculator newExpression = new Calculator(expression, result, isError);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newExpression);
            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            log.info("Transaction rollback. Method register(User)");
            entityManager.getTransaction().rollback();
            log.error(ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void saveCalculator(Calculator calculator) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(calculator);
            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            log.info("Transaction rollback. Method register(User)");
            entityManager.getTransaction().rollback();
            log.error(ex);
            ex.printStackTrace();
        }
    }

    @Override
    public User getUserById(long id) {
        Query query = entityManager.createQuery("SELECT a FROM User a where a.id =:var", User.class);
        query.setParameter("var", id);
        return (User) query.getResultList().get(0);
    }
}
