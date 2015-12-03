package on2015_11.on2015_11_22_Grand_Prix_of_Europe___2015.G___Greenhouse_Growth;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class TaskGTestCase {
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
        int testCount = 10000;
        int n = 10;
        int max = 10;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
            out.printLine(n, n);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = random.nextInt(max) + 1;
            }
            out.printLine(a);
            char[] x = new char[n];
            for (int i = 0; i < n; i++) {
                x[i] = random.nextBoolean() ? 'A' : 'B';
            }
            out.printLine(x);
            for (int i = 0; i < n; i++) {
                if (x[i] == 'A') {
                    for (int j = 1; j < n; j++) {
                        if (a[j] < a[j - 1]) {
                            a[j]++;
                        }
                    }
                } else {
                    for (int j = n - 2; j >= 0; j--) {
                        if (a[j] < a[j + 1]) {
                            a[j]++;
                        }
                    }
                }
            }
            outAnswer.printLine(a);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
