package on2015_08.on2015_08_22_SNSS_2015_R4.A______________;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class TaskATestCase {
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
        int testCount = 0;
        for (int n = 1; n <= 6; n++) {
            int edges = n * (n - 1) / 2;
            int[] a = new int[edges];
            int[] b = new int[edges];
            int at = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    a[at] = i + 1;
                    b[at++] = j + 1;
                }
            }
            for (int j = 0; j < (1 << edges); j++) {
                StringWriter sw = new StringWriter();
                OutputWriter out = new OutputWriter(sw);
                StringWriter swAnswer = new StringWriter();
                OutputWriter outAnswer = new OutputWriter(swAnswer);
                out.printLine(n, Integer.bitCount(j));
                for (int i = 0; i < edges; i++) {
                    if ((j >> i & 1) == 1) {
                        out.printLine(a[i], b[i]);
                    }
                }
                int answer = -1;
                int diff = n + 1;
                for (int i = 0; i < (1 << n); i++) {
                    boolean ok = true;
                    for (int k = 0; k < edges; k++) {
                        if ((j >> k & 1) == 1 && ((i >> (n - a[k]) & 1) == (i >> (n - b[k]) & 1))) {
                            ok = false;
                            break;
                        }
                    }
                    int cDiff = Math.abs(n - 2 * Integer.bitCount(i));
                    if (ok && cDiff < diff) {
                        answer = i;
                        diff = cDiff;
                    }
                }
                if (diff == n + 1) {
                    outAnswer.printLine("impossible");
                } else {
                    for (int i = n - 1; i >= 0; i--) {
                        outAnswer.print((answer >> i & 1) == 1 ? 'B' : 'A');
                    }
                    outAnswer.printLine();
                }
                tests.add(new Test(sw.toString(), swAnswer.toString()));
            }
        }
        for (int n = 7; n <= 12; n++) {
            int edges = n * (n - 1) / 2;
            int[] a = new int[edges];
            int[] b = new int[edges];
            int at = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    a[at] = i + 1;
                    b[at++] = j + 1;
                }
            }
            boolean[] hasEdge = new boolean[edges];
            for (int j = 0; j < 1000; j++) {
                StringWriter sw = new StringWriter();
                OutputWriter out = new OutputWriter(sw);
                StringWriter swAnswer = new StringWriter();
                OutputWriter outAnswer = new OutputWriter(swAnswer);
                int answer = -1;
                int e = 0;
                for (int i = 0; i < edges; i++) {
                    hasEdge[i] = random.nextInt(10) == 0;
                    if (hasEdge[i]) {
                        e++;
                    }
                }
                int diff = n + 1;
                for (int i = 0; i < (1 << n); i++) {
                    boolean ok = true;
                    for (int k = 0; k < edges; k++) {
                        if (hasEdge[k] && ((i >> (n - a[k]) & 1) == (i >> (n - b[k]) & 1))) {
                            ok = false;
                            break;
                        }
                    }
                    int cDiff = Math.abs(n - 2 * Integer.bitCount(i));
                    if (ok && cDiff < diff) {
                        answer = i;
                        diff = cDiff;
                    }
                }
                if (diff == n + 1) {
                    outAnswer.printLine("impossible");
                } else {
                    for (int i = n - 1; i >= 0; i--) {
                        outAnswer.print((answer >> i & 1) == 1 ? 'B' : 'A');
                    }
                    outAnswer.printLine();
                }
                out.printLine(n, e);
                for (int i = 0; i < edges; i++) {
                    if (hasEdge[i]) {
                        out.printLine(a[i], b[i]);
                    }
                }
                tests.add(new Test(sw.toString(), swAnswer.toString()));
            }
        }
        return tests;
    }
}
