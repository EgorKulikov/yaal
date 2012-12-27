package on2012_12.on2012_12_27_Codeforces_Round__158.E___Dividing_Kingdom;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import net.egork.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class TaskETestCase0 implements TestProvider {
    public Collection<Test> createTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 5;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            int n = 100000;
            out.printLine(n);
            for (int i = 0; i < n; i++)
                out.printLine(random.nextInt((int) (2e9 + 1)) - 1, random.nextInt((int) (2e9 + 1)) - 1);
            out.printLine(n / 9 + 1);
            for (int i = 0; i < 8; i++)
                out.printLine(n / 9);
            tests.add(new Test(sw.toString()));
        }
        return tests;
    }
}
