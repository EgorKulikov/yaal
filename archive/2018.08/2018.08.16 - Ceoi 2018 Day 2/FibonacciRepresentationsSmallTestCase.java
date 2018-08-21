package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.lang.Integer.bitCount;
import static java.util.Arrays.copyOfRange;
import static net.egork.misc.MiscUtils.MOD7;
import static net.egork.numbers.IntegerUtils.generateFibonacci;

public class FibonacciRepresentationsSmallTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            out.printLine(100000);
            for (int i = 0; i < 100000; i++) {
//                out.printLine(1000000000);
                out.printLine(random.nextInt(1000000000) + 1);
            }
            tests.add(new Test(sw.toString()));
        }
        return tests;
    }

    long[] f;
    Map<IntIntPair, Integer> answer = new HashMap<>();

    @TestCase
    public Collection<Test> accuracyTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        f = generateFibonacci(20, MOD7);
        f = copyOfRange(f, 1, f.length);
        for (int i = 1; i < 0/*(1 << 15)*/; i++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
            out.printLine(bitCount(i));
            for (int j = 0; j < 15; j++) {
                if ((i >> j & 1) == 1) {
                    out.printLine(j + 1);
                }
            }
            solve(i, outAnswer);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }

    private int solve(int value, int index) {
        if (value == 0) {
            return 1;
        }
        if (index == -1) {
            return 0;
        }
        IntIntPair key = new IntIntPair(value, index);
        if (answer.containsKey(key)) {
            return answer.get(key);
        }
        int result = solve(value, index - 1);
        if (value >= f[index]) {
            result += solve((int) (value - f[index]), index - 1);
        }
        answer.put(key, result);
        return result;
    }

    private void solve(int i, OutputWriter out) {
        int current = 0;
        for (int j = 0; j < 15; j++) {
            if ((i >> j & 1) == 1) {
                current += f[j];
                out.printLine(solve(current, f.length - 1));
            }
        }
    }
}
