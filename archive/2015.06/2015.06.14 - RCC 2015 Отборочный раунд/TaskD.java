package net.egork;

import net.egork.collections.intcollection.IntPair;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Stack;

public class TaskD {
    private static final long MOD = (long) (1e9 + 7);
    long[] factorial = IntegerUtils.generateFactorial(400001, MOD);
    long[] reverseFactorial = IntegerUtils.generateReverseFactorials(200001, MOD);
    long[] reverse = IntegerUtils.generateReverse(200002, MOD);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] priority = IOUtils.readIntArray(in, count);
        Stack<IntPair> stack = new Stack<>();
        long answer = 1;
        for (int i = 0; i < count; i++) {
            while (!stack.isEmpty() && stack.peek().first < priority[i]) {
                answer *= calculate(stack.pop().second);
                answer %= MOD;
            }
            if (stack.isEmpty() || stack.peek().first > priority[i]) {
                stack.add(new IntPair(priority[i], 1));
            } else {
                stack.add(new IntPair(priority[i], stack.pop().second + 1));
            }
        }
        while (!stack.isEmpty()) {
            answer *= calculate(stack.pop().second);
            answer %= MOD;
        }
        out.printLine(answer);
    }

    private long calculate(int count) {
        return factorial[2 * count] * reverseFactorial[count] % MOD * reverseFactorial[count] % MOD * reverse[count + 1] % MOD;
    }
}
