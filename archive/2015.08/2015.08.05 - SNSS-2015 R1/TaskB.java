package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.math.BigInteger;

public class TaskB {
    BigInteger[][] answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        answer = new BigInteger[k + 1][n - k + 1];
        out.printLine(calculate(k, n - k));
    }

    private BigInteger calculate(int floors, int offices) {
        if (offices < 0) {
            return BigInteger.ZERO;
        }
        if (answer[floors][offices] != null) {
            return answer[floors][offices];
        }
        if (floors == 0) {
            return answer[floors][offices] = offices == 0 ? BigInteger.ONE : BigInteger.ZERO;
        }
        return answer[floors][offices] = calculate(floors - 1, offices).add(calculate(floors, offices - floors));
    }
}
