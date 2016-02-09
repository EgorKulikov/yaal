package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class SnakesAndLaddersTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 0;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            tests.add(new Test(sw.toString()));
        }
        return tests;
    }

    @TestCase
    public Collection<Test> accuracyTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 200;
        int n = 100;
        int mh = 10;
        int[] x = new int[n];
        int[] h = new int[n];
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            out.printLine(1);
            out.printLine(n);
            do {
                for (int i = 0; i < n; i++) {
                    x[i] = random.nextInt(1000000000) + 1;
                }
            } while (new IntHashSet(x).size() != n);
            for (int i = 0; i < n; i++) {
                h[i] = random.nextInt(mh) + 1;
            }
            for (int i = 0; i < n; i++) {
                out.printLine(x[i], h[i]);
            }
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
            outAnswer.printLine("Case #1:", dumb(n, x, h));
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }

    private long dumb(int n, int[] x, int[] h) {
        long answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (h[i] != h[j]) {
                    continue;
                }
                boolean good = true;
                for (int k = 0; k < n; k++) {
                    if (h[k] > h[i] && x[k] > Math.min(x[i], x[j]) && x[k] < Math.max(x[i], x[j])) {
                        good = false;
                        break;
                    }
                }
                if (good) {
                    long dx = x[i] - x[j];
                    answer += dx * dx;
                    answer %= SnakesAndLadders.MOD;
                }
            }
        }
        return answer;
    }
}
