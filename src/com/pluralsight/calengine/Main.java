package com.pluralsight.calengine;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        performCalculations();
    }

    static void performCalculations() {
        MathEquation[] equations = new MathEquation[4];
        equations[0] = create(100.0d, 50.0d, 'd');
        equations[1] = create(25.0d, 92.0d, 'a');
        equations[2] = create(225.0d, 17.0d, 's');
        equations[3] = create(11.0d, 3.0d, 'm');

        for (MathEquation equation : equations) {
            equation.execute();
            System.out.println("result = " + equation.getResult());
        }

    }


    private static MathEquation create(double leftVal, double rightVal, char opCode) {
        MathEquation equation = new MathEquation();
        equation.leftVal = leftVal;
        equation.rightVal = rightVal;
        equation.opCode = opCode;

        return equation;
    }


    // We scan the user input and pass it to get parsed
    static void executeInteractively() {
        System.out.println("Enter an operation and two numbers:");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String[] parts = userInput.split(" ");
        performOperation(parts);
    }


    // This allows to get the input from user and correctly categorize
    private static void performOperation(String[] parts) {
        char opCode = opCodeFromString(parts[0]);
        if (opCode == 'w')
            handleWhen(parts);
        else {
            double leftVal = valueFromWord(parts[1]);
            double rightVal = valueFromWord(parts[2]);
//            double result = execute(opCode, leftVal, rightVal);
//            displayResult(opCode, leftVal, rightVal, result);
        }
    }

    // allows days to adding to a date
    private static void handleWhen(String[] parts) {
        LocalDate startDate = LocalDate.parse(parts[1]);
        long daysToAdd = (long) valueFromWord(parts[2]);
        LocalDate newDate = startDate.plusDays(daysToAdd);
        String output = String.format("%s plus %d days is %s", startDate, daysToAdd, newDate);
        System.out.println(output);
    }

    // String builder removed and String format used
    private static void displayResult(char opCode, double leftVal, double rightVal, double result) {
        char symbol = symbolFromOpCode(opCode);
        String output = String.format("%.3f %c %.3f = %.3f", leftVal, symbol, rightVal, result);
        System.out.println(output);
    }


    private static char symbolFromOpCode(char opCode) {
        char[] opCodes = {'a', 's', 'm', 'd'};
        char[] symbols = {'+', '-', '*', '/'};
        char symbol = ' ';
        for (int index = 0; index < opCodes.length; index++) {
            if (opCode == opCodes[index]) {
                symbol = symbols[index];
                break;
            }
        }

        return symbol;
    }

    private static void handleCommandLine(String[] args) {
        char opCode = args[0].charAt(0);
        double leftVal = Double.parseDouble(args[1]);
        double rightVal = Double.parseDouble(args[2]);
//        double result = execute(opCode, leftVal, rightVal);
//        System.out.println(result);
    }

    static char opCodeFromString(String operationName) {
        char opCode = operationName.charAt(0);
        return opCode;
    }

    static double valueFromWord(String word) {
        String[] numberWords = {
                "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine"
        };
        double value = -1d;
        for (int index = 0; index < numberWords.length; index++) {
            if (word.equals(numberWords[index])) {
                value = index;
                break;
            }
        }
        if (value == -1d)
            value = Double.parseDouble(word);

        return value;
    }
}
