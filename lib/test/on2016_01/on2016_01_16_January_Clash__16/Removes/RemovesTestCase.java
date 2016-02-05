package on2016_01.on2016_01_16_January_Clash__16.Removes;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class RemovesTestCase {
    Random r = new Random(239);
    int testCount = 50;

    int f(int a, int b) {
        double x = Math.log(a) + r.nextDouble() * (Math.log(b) - Math.log(a));
        return (int) Math.round(Math.floor(Math.exp(x)));
    }

    @TestCase
    public Collection<Test> type1() {
        List<Test> tests = new ArrayList<Test>();
        int testCount = this.testCount;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            int n = f(50, 500);
            int B = f(10000000, 100000000);
            out.printLine(1, n, B);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        out.print("0 ");
                    } else {
                        out.print(f(1, 1000000) + " ");
                    }
                }
                out.printLine();
            }
            tests.add(new Test(sw.toString()));
        }
        return tests;
    }

    @TestCase
    public Collection<Test> type2() {
        List<Test> tests = new ArrayList<Test>();
        int testCount = this.testCount;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            int n = f(50, 500);
            int B = f(10000000, 100000000);
            out.printLine(2, n, B);
            int[][] c = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        c[i][j] = r.nextInt(1000001);
                    }
                }
            }
            int k = r.nextInt(4) + 1;
            for (int i = 0; i < k; i++) {
                double p = .25 + .5 * r.nextDouble();
                int need = (int) Math.round(Math.floor(p * n));
                IntSet set = new IntHashSet();
                for (int j = 0; j < n; j++) {
                    if (r.nextDouble() <= (need - set.size()) / (n - j)) {
                        set.add(j);
                    }
                }
                double q = 0.75 + 0.25 * r.nextDouble();
                int x = 1 + r.nextInt(20);
                for (int m = 0; m < x; m++) {
                    for (int j = 0; j < n; j++) {
                        for (int l = 0; l < n; l++) {
                            if (set.contains(j) && !set.contains(l) && r.nextDouble() <= q) {
                                c[j][l] = r.nextInt(c[j][l] + 1);
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                out.printLine(c[i]);
            }
            tests.add(new Test(sw.toString()));
        }
        return tests;
    }
}
