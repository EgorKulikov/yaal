package on2016_03.on2016_03_20_Grand_Prix_of_Baltics___2016.B___Hovels;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class TaskBTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        if (true) {
            return tests;
        }
        Random random = new Random(239);
        List<IntIntPair> all = new ArrayList<>();
        int x = 3;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                all.add(new IntIntPair(i + 1, j + 1));
            }
        }
        for (int i = 0; i < x * x; i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    for (int l = 0; l < x * x; l++) {
                        if (l == i || l == j || l == k) {
                            continue;
                        }
                        for (int m = 0; m < l; m++) {
                            if (m == i || m == j || m == k) {
                                continue;
                            }
                            for (int n = 0; n < m; n++) {
                                if (n == i || n == j || n == k) {
                                    continue;
                                }
                                StringWriter sw = new StringWriter();
                                OutputWriter out = new OutputWriter(sw);
                                out.printLine(all.get(i).first, all.get(i).second);
                                out.printLine(all.get(j).first, all.get(j).second);
                                out.printLine(all.get(k).first, all.get(k).second);
                                out.printLine(all.get(l).first, all.get(l).second);
                                out.printLine(all.get(m).first, all.get(m).second);
                                out.printLine(all.get(n).first, all.get(n).second);
                                tests.add(new Test(sw.toString()));
                            }
                        }
                    }
                }
            }
        }
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
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
