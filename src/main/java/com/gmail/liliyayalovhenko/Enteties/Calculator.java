package com.gmail.liliyayalovhenko.Enteties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Deque;
import java.util.LinkedList;

@Entity
@Table(name="CALCULATOR")
public class Calculator {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="EXPR", nullable = false)
    private String expression;

    @Column(name="RESULT", nullable = false, precision=10, scale=3 )
    private double result;
    private int numChar;
    private char curChar;
    private boolean isDouble;

    @Column(name="ERROR", nullable = false)
    private boolean noError;

    public Calculator(String expression, double result, boolean noError) {
        this.expression = expression;
        this.result = result;
        this.noError = noError;
    }

    public Calculator() {}

    public void calculateExpression() {
        Deque<Double> operands = new LinkedList<>();
        Deque<Character> operators = new LinkedList<>();

        if (expression.length() == 0) {
            System.out.println("Error! String is empty!");
        } else {
            /**
             * () is added to pop all the remaining operations: +, -, /, *
             * Without wrapping in () result will be reverse polish notation
             * F - means the end of parsing expression
             * **/
            setExpression("(" + expression + ")" + "F");
            noError = true;
            numChar = 0;
            getNextChar();

            while (noError && (curChar != 'F')) {
                if (curChar == ' ') {
                    getNextChar();
                } else if (isOperator(curChar)) {
                    if (curChar == ')') {
                        calculateInParentheses(operands, operators);
                        operators.poll();
                    } else {
                        try {
                            while (canPollOperators(curChar, operators) && noError) {
                                pollOperators(operands, operators);
                            }
                            operators.push(curChar);
                        } catch (Exception e) {
                            noError = false;
                            System.out.println("Error!!! Problem with current symbol " + curChar);
                            System.out.println(e);
                        }
                    }
                    getNextChar();
                } else if (isDigit(curChar)) {
                    double numb = 0;
                    double doublePart;
                    double doubleDivider = 10;
                    while (isDigit(curChar) && (numChar < expression.length()) && noError) {
                        if (curChar == '.' && numb != 0) {
                            isDouble = true;
                            getNextChar();
                        } else if (isDouble) {
                            doublePart = "0123456789".indexOf(curChar) / doubleDivider;
                            numb = numb + doublePart;
                            doubleDivider *= 10;
                            getNextChar();
                        } else if (curChar == ' ') {
                            getNextChar();
                        } else {
                            numb = numb * 10 + "0123456789".indexOf(curChar);
                            getNextChar();
                        }
                    }
                    isDouble = false;

                    /**following 3 lines of code are used to round
                     * double to write format. As in Java, double
                     * values are IEEE floating point numbers.**/
                    BigDecimal roundedNumber = getRoundedNumber(numb, doubleDivider);
                    operands.push(roundedNumber.doubleValue());
                } else {
                    System.out.println("Error: unknown symbol <" + curChar + " >'");
                    noError = false;
                }
            }
        }
        result = noError ? operands.poll() : 0;
    }

    public void getNextChar() {
        curChar = expression.charAt(numChar++);
    }

    public boolean isOperator(char currentChar) {
        return (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/' ||
                currentChar == '(' || currentChar == ')');
    }

    public boolean isDigit(char currentChar) {
        try {
            return ((currentChar - '0') <= 9 && 0 <= (currentChar - '0') || (currentChar == '.' &&
                    (expression.charAt(numChar - 2) - '0') >= 0 && (expression.charAt(numChar - 2) - '0') <= 9));
        } catch (NullPointerException e) {
            return false;
        }
    }

    private BigDecimal getRoundedNumber(double numb, double doubleDivider) {
        BigDecimal roundedNumber = new BigDecimal(numb);
        int scale = (int) Math.log10(doubleDivider);
        roundedNumber = roundedNumber.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return roundedNumber;
    }

    private int getPriority(char operator) throws Exception {
        switch (operator) {
            case '(':
                return -1;
            case '*':
            case '/':
                return 1;
            case '+':
            case '-':
                return 2;
            default:
                throw new Exception("Not supported operations: char - " + operator);
        }
    }

    private boolean canPollOperators(char operator1, Deque<Character> operators) throws Exception {
        if (operators.size() == 0) {
            return false;
        }

        int prior1 = getPriority(operator1);
        int prior2 = getPriority(operators.peek());
        return prior1 >= 0 && prior2 >= 0 && prior1 >= prior2;
    }

    private void pollOperators(Deque<Double> operands, Deque<Character> operators) {
        double b, a;
        char sign;
        try {
            b = operands.poll();
            a = operands.poll();
            sign = operators.poll();

            if (sign == '+') {
                operands.push(a + b);

            } else if (sign == '-') {
                operands.push(a - b);

            } else if (sign == '*') {
                operands.push(a * b);

            } else if (sign == '/') {
                operands.push(a / b);

            }
        } catch (NullPointerException e) {
            noError = false;
            System.out.println("Wrong format of expression! Please put valid expression!");
        }
    }

    private void calculateInParentheses(Deque<Double> operands, Deque<Character> operators) {
        while (noError && operators.size() > 0 && operators.peek() != '(')
            pollOperators(operands, operators);
    }

    public double getResult() {
        return result;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public boolean isNoError() {
        return noError;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setNoError(boolean noError) {
        this.noError = noError;
    }
}

