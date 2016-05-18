# CalculatorWEB
Calculator project

Used technologies: Java Core, Maven, Junit, Spring framework(web MVC for web-interface), jstl, Hibernate, MySql.

Supported operations: +, -, *, /, (, )

Return as result a number or "0.0" and error message if string is in wrong format.
All logs are in log file

Additionals:

1. Record entered by the user the expression string to the database as well as result (relational - MySQL)

2. Logging of runtime program execution

3. User Registration, saving username, password and all user's calculated expression to database.

Иной вариант реализации программы

1) Алгоритм функционирования конечного автомата.
2) Лексический анализ - преобразование строки в массив лексем. Синтаксический анализ – преобразование массива лексем в бинарное дерево или сразу вычислить значение для арифметического выражения);
3) Для реализации веб-интерфейса мспользовать SparkJava фреймворк (для этого задания не успела освоить этот фреймворк, поэтому использовала Spring MVC, Hibernate)

Направления развития

Добавление функции двух, одной переменной;
Добавление функций с произвольным количеством переменных;
Добавление ‘%’ – остаток от деления;
Построить логирование выполнения программы с помощью  AOP.

<img src="/Снимок экрана от 2016-05-18 19-37-18.png">
