package org.gepardec.structured_logging;

import org.slf4j.MDC;

public class SumOperation implements CalcOperation {

    @Override
    public int performCalculation(int op1, int op2) {
        return op1 + op2;
    }
}
