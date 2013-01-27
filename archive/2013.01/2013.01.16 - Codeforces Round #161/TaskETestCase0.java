package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import net.egork.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class TaskETestCase0 implements TestProvider {
    public Collection<Test> createTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            out.printLine(1000, 1000, 50);
            for (int i = 0; i < 1000 * 1000; i++)
                out.printLine(random.nextInt(1000001));
            tests.add(new Test(sw.toString()));
        }
        return tests;
    }
}
