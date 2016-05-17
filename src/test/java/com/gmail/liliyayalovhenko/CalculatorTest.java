package com.gmail.liliyayalovhenko;

import com.gmail.liliyayalovhenko.Enteties.Calculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CalculatorTest {

    @Test(timeout = 1000)
    public void testCalculateExpression() throws Exception {

        Calculator calculator = new Calculator();

        calculator.setExpression("123.12 + 34 * (12 - 10)");
        calculator.calculateExpression();
        double result = calculator.getResult();
        assertEquals(191.12, result, 0.0);

        calculator.setExpression(".5");
        calculator.calculateExpression();
        result = calculator.getResult();
        assertEquals(0, result, 0.0);

        calculator.setExpression("2147483647 + 1");
        calculator.calculateExpression();
        result = calculator.getResult();
        assertEquals(2.147483648E9, result, 0.0);

        calculator.setExpression("(1 - (2 * 3) +((5-1)) - 2 * 3 + ((80 / 2 - 15)))");
        calculator.calculateExpression();
        result = calculator.getResult();
        assertEquals(18.0, result, 0.0);
    }



    @Test(timeout = 100)
    public void testIsOperator() throws Exception {
        Calculator calculator = new Calculator();
        assertTrue(calculator.isOperator('+'));
        assertTrue(calculator.isOperator('-'));
        assertTrue(calculator.isOperator('*'));
        assertTrue(calculator.isOperator('/'));
        assertTrue(calculator.isOperator('('));
        assertTrue(calculator.isOperator(')'));
        assertFalse(calculator.isOperator('^'));
    }

    @Test(timeout = 100)
    public void testIsDigit() throws Exception {
        Calculator calculator = new Calculator();
        assertTrue(calculator.isDigit('9'));
        assertTrue(calculator.isDigit('0'));
        assertFalse(calculator.isDigit('.'));
    }
}