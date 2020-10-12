package com.pluralsight.calengine;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        performCalculations();

//        Divider divider = new Divider();
//        doCalculation(divider, 100.0d, 50.0d);
//
//        Adder adder = new Adder();
//        doCalculation(adder, 25.0d, 92.0d);
        
//        performMoreCalculations();
//
        executeInteractively();

    }

    private static CalculateBase createCalculation(MathOperation operation, double leftVal, double rightVal) {
        // create different types
        CalculateBase calculation = null;

        // since enum we can use switch case
        switch(operation){
            // when we see ADD we do ADD, same for other operations
            case ADD:
                calculation = new Adder(leftVal, rightVal);
                break;
            case SUBTRACT:
                calculation = new Subtracter(leftVal, rightVal);
                break;
            case MULTIPLY:
                calculation = new Multiplier(leftVal, rightVal);
                break;
            case DIVIDE:
                calculation = new Divider(leftVal, rightVal);
                break;
        }

        return calculation;
    }

    private static void performMoreCalculations() {
        CalculateBase[] calculations = {
                new Divider(100.0d, 50.0d),
                new Adder(25.0d, 92.0d),
                new Subtracter(225.0d, 17.0d),
                new Multiplier(11.0d, 3.0d),

        };
        System.out.println();
        System.out.println("Array Calculations");

        // walk through array and do calculations
        for(CalculateBase calculation : calculations){

            calculation.calculate();

            System.out.println("result = " + calculation.getResult());
        }
    }

    static void doCalculation(CalculateBase calculation, double leftVal, double rightVal) {
        calculation.setLeftVal(leftVal);
        calculation.setRightVal(rightVal);
        calculation.calculate();
        System.out.println("Calculation result = " + calculation.getResult());

    }

    static void performCalculations() {
        MathEquation[] equations = new MathEquation[4];
        equations[0] = new MathEquation('d', 100.0d, 50.0d);
        equations[1] = new MathEquation('a', 25.0d, 92.0d);
        equations[2] = new MathEquation('s', 225.0d, 17.0d);
        equations[3] = new MathEquation('m', 11.0d, 3.0d);

        for (MathEquation equation : equations) {
            equation.execute();
            System.out.println("result = " + equation.getResult());
        }

        System.out.println("Average result = " + MathEquation.getAverageResult());

        System.out.println();
        System.out.println("Using execute overloads");
        System.out.println();

        MathEquation equationOverload = new MathEquation('d');
        double leftDouble = 9.0d;
        double rightDouble = 4.0d;

        equationOverload.execute(leftDouble, rightDouble);
        System.out.println("Overloaded result with doubles: " + equationOverload.getResult());

        // Java auto widens int to double value - It works!
        double leftInt = 90;
        double rightInt = 4;

        equationOverload.execute(leftInt, rightInt);
        System.out.println("Overloaded result with doubles: " + equationOverload.getResult());
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
        // toUpper used to make checking work correctly ADD = ADD
        MathOperation operation = MathOperation.valueOf(parts[0].toUpperCase());

        // translate string to double
        double leftVal = Double.parseDouble((parts[1]));
        double rightVal = Double.parseDouble((parts[2]));
        CalculateBase calculation =  createCalculation(operation, leftVal, rightVal);

        calculation.calculate();

        System.out.println("Operation performed: " + operation);
        System.out.println(calculation.getResult());
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
