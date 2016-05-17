package com.gmail.liliyayalovhenko;

import com.gmail.liliyayalovhenko.Enteties.Calculator;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setExpression("1 - 2 * 3 +5-1 - 2 * 3 + (80 / 2 - 15)");
        calculator.calculateExpression();
        double result = calculator.getResult();
        System.out.println(calculator.isNoError() ? result : "Error, please try again with valid format of " +
                "expression!\nSee log file to find the problem.");
    }
}
