package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class Safe {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        char[] type = new char[count];
        int[] operand = new int[count];
        for (int i = 0; i < count; i++) {
            type[i] = in.readCharacter();
            operand[i] = in.readInt();
        }
        BigInteger totalMultiply = BigInteger.ONE;
        BigInteger totalDivide = BigInteger.ONE;
        int totalAdd = 0;
        int totalSubtract = 0;
        for (int i = 0; i < count; i++) {
            if (type[i] == '*')
                totalMultiply = totalMultiply.multiply(BigInteger.valueOf(operand[i]));
            else if (type[i] == '/')
                totalDivide = totalDivide.multiply(BigInteger.valueOf(operand[i]));
            else if (type[i] == '+')
                totalAdd += operand[i];
            else if (type[i] == '-')
                totalSubtract += operand[i];
        }
        String order;
        if (totalMultiply.compareTo(totalDivide) > 0) {
            if (totalMultiply.multiply(BigInteger.valueOf(totalAdd)).compareTo(BigInteger.valueOf(totalSubtract)) >= 0)
                order = "/+*-";
            else
                order = "+*-/";
        } else {
            if (totalDivide.multiply(BigInteger.valueOf(totalAdd)).compareTo(BigInteger.valueOf(totalSubtract)) >= 0)
                order = "-/+*";
            else
                order = "*-/+";
        }
        for (char c : order.toCharArray()) {
            for (int i = 0; i < count; i++) {
                if (c == type[i])
                    out.printLine(type[i], operand[i]);
            }
        }
    }
}
