package com.pluralsight.calengine;

public class Subtracter extends CalculateBase {
    @Override
    public void calculate() {
        double value = getLeftVal() - getLeftVal();
        setResult(value);
    }
}