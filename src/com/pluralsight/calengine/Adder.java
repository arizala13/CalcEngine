package com.pluralsight.calengine;

public class Adder extends CalculateBase {
    public void calculate() {
        double value = getLeftVal() + getRightVal();
        setResult(value);
    }
}
