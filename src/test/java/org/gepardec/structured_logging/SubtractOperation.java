package org.gepardec.structured_logging;

public class SubtractOperation implements CalcOperation {
    @Override
    public int performCalculation(int op1, int op2) {
        return op1 - op2;
    }
}
