package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class D {
    boolean[] value;
    boolean[] relevant;
    Element[] elements;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        elements = new Element[n];
        for (int i = 0; i < n; i++) {
            elements[i] = new Element(in);
        }
        value = new boolean[n];
        calculate(0);
        relevant = new boolean[n];
        calculateRelevance(0);
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (elements[i].operation == Operation.IN) {
                answer.append((value[0] ^ relevant[i]) ? 1 : 0);
            }
        }
        out.printLine(answer);
    }

    private void calculateRelevance(int at) {
        relevant[at] = true;
        if (elements[at].operation == Operation.NOT) {
            calculateRelevance(elements[at].args[0] - 1);
            return;
        }
        if (elements[at].operation == Operation.XOR) {
            calculateRelevance(elements[at].args[0] - 1);
            calculateRelevance(elements[at].args[1] - 1);
            return;
        }
        if (elements[at].operation == Operation.OR) {
            if (!value[elements[at].args[1] - 1]) {
                calculateRelevance(elements[at].args[0] - 1);
            }
            if (!value[elements[at].args[0] - 1]) {
                calculateRelevance(elements[at].args[1] - 1);
            }
            return;
        }
        if (elements[at].operation == Operation.AND) {
            if (value[elements[at].args[1] - 1]) {
                calculateRelevance(elements[at].args[0] - 1);
            }
            if (value[elements[at].args[0] - 1]) {
                calculateRelevance(elements[at].args[1] - 1);
            }
            return;
        }
    }

    private boolean calculate(int at) {
        boolean result;
        switch (elements[at].operation) {
        case IN:
            result = elements[at].args[0] == 1;
            break;
        case OR:
            calculate(elements[at].args[0] - 1);
            calculate(elements[at].args[1] - 1);
            result = value[elements[at].args[0] - 1] || value[elements[at].args[1] - 1];
            break;
        case AND:
            calculate(elements[at].args[0] - 1);
            calculate(elements[at].args[1] - 1);
            result = value[elements[at].args[0] - 1] && value[elements[at].args[1] - 1];
            break;
        case NOT:
            result = !calculate(elements[at].args[0] - 1);
            break;
        case XOR:
            calculate(elements[at].args[0] - 1);
            calculate(elements[at].args[1] - 1);
            result = value[elements[at].args[0] - 1] ^ value[elements[at].args[1] - 1];
            break;
        default:
            throw new RuntimeException();
        }
        return value[at] = result;
    }

    static class Element {
        Operation operation;
        int[] args;

        Element(InputReader in) {
            operation = Operation.valueOf(in.readString());
            args = in.readIntArray(operation.args);
        }
    }

    enum Operation {
        IN(1),
        NOT(1),
        XOR(2),
        AND(2),
        OR(2);

        int args;
        Operation(int args) {
            this.args = args;
        }
    }
}
