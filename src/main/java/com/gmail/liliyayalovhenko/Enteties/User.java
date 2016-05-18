package com.gmail.liliyayalovhenko.Enteties;


import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="USER")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Calculator> allExprOfUser;

    private static final Logger log = Logger.getLogger(User.class);

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {}

    public User(String username, String password, List<Calculator> allExprOfUser) {
        this.username = username;
        this.password = password;
        this.allExprOfUser = allExprOfUser;
    }

    public void addExpression(Calculator calculator) {
        log.info("Method addExpression(Calculator). Start to set calculator to User. Id -  " + this.id +", calculator_id " + calculator.getId());
        allExprOfUser.add(calculator);
        log.info("Method addExpression(Calculator). Calculator_id " + calculator.getId() + " added!");

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Calculator> getAllExprOfUser() {
        return allExprOfUser;
    }

    public void setAllExprOfUser(List<Calculator> allExprOfUser) {
        this.allExprOfUser = allExprOfUser;
    }

}

