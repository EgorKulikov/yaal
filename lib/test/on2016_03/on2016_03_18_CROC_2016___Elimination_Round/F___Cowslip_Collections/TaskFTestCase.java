package on2016_03.on2016_03_18_CROC_2016___Elimination_Round.F___Cowslip_Collections;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.TestCase;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class TaskFTestCase {
    private static final long MOD = (long) (1e9 + 7);

    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 0;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            out.printLine(100000, 1, 100000);
            for (int i = 0; i < 200000; i++) {
                out.printLine(random.nextInt(1000000) + 1);
            }
            tests.add(new Test(sw.toString()));
        }
        return tests;
    }

    @TestCase
    public Collection<Test> accuracyTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 10;
        int n = 1;
        int q = 3;
        int k = 2;
        int lim = 100;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
            out.printLine(n, k, q);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = random.nextInt(lim) + 1;
            }
            int[] c = new int[q];
            for (int i = 0; i < q; i++) {
                c[i] = random.nextInt(lim) + 1;
            }
            out.printLine(a);
            out.printLine(c);
//            long[][] cc = IntegerUtils.generateBinomialCoefficients(n + q + 1, MOD);
            int[] all = Arrays.copyOf(a, n + q);
            for (int i = 0; i < q; i++) {
                all[n + i] = c[i];
                long answer = 0;
                for (int j = 0; j <= n + i; j++) {
                    for (int l = 0; l < j; l++) {
                        answer += IntegerUtils.gcd(all[j], all[l]);
                    }
                }
                outAnswer.printLine(answer);
            }
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
