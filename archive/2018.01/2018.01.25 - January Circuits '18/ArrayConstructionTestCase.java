package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.io.OutputWriter;
import net.egork.misc.ExtendedRandom;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static net.egork.misc.ArrayUtils.createOrder;

public class ArrayConstructionTestCase {
    @TestCase
    public Collection<Test> tests() {
        List<Test> tests = new ArrayList<Test>();
        ExtendedRandom random = new ExtendedRandom(239);
        for (int testNumber = 0; testNumber < ArrayConstructionChecker.TESTS_PER_TYPE; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            int n = 32;
            out.printLine(n, random.nextInt(4072, 4089));
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i < j) {
                        out.printLine(random.nextInt((1 << 20) + 1));
                    } else {
                        out.printLine(0);
                    }
                }
            }
            tests.add(new Test(sw.toString()));
        }
        for (int testNumber = 0; testNumber < ArrayConstructionChecker.TESTS_PER_TYPE; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            int n = 256;
            out.printLine(n, random.nextInt(3841, 4097));
            int[] p = createOrder(n);
            for (int i = 0; i < n; i++) {
                int id = random.nextInt(i + 1);
                int temp = p[id];
                p[id] = p[i];
                p[i] = temp;
            }
            int[] q = new int[n];
            q[p[0]] = -1;
            for (int i = 1; i < n; i++) {
                q[p[i]] = p[random.nextInt(i)];
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (j == q[i]) {
                        out.printLine(random.nextInt((1 << 22) + 1));
                    } else {
                        out.printLine(0);
                    }
                }
            }
            tests.add(new Test(sw.toString()));
        }
        for (int testNumber = 0; testNumber < ArrayConstructionChecker.TESTS_PER_TYPE; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            int n = 256;
            out.printLine(n, random.nextInt(3841, 4097));
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i < j) {
                        out.printLine(random.nextInt((1 << 14) + 1));
                    } else {
                        out.printLine(0);
                    }
                }
            }
            tests.add(new Test(sw.toString()));
        }
        return tests;
    }
}
