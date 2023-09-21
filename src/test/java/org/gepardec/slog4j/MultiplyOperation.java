package org.gepardec.slog4j;

public class MultiplyOperation implements CalcOperation{
    @Override
    public int performCalculation(int op1, int op2) {
        return op1 * op2;
    }
}
