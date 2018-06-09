package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class TaskDTestCase {
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
        for (int n = 1; n <= 10; n++) {
            for (int d1 = 1; d1 <= 10; d1++) {
                for (int d2 = 1; d2 < d1; d2++) {
                    StringWriter sw = new StringWriter();
                    OutputWriter out = new OutputWriter(sw);
                    StringWriter swAnswer = new StringWriter();
                    OutputWriter outAnswer = new OutputWriter(swAnswer);
                    out.printLine(n, d1, d2);
                    tests.add(new Test(sw.toString(), swAnswer.toString()));
                }
            }
        }
        return tests;
    }
}
