package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.TestCase;

import net.egork.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

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
        int n = 16;
        for (int a = 2; a < n; a++) {
            for (int b = 0; b < n; b++) {
                StringWriter sw = new StringWriter();
                OutputWriter out = new OutputWriter(sw);
                StringWriter swAnswer = new StringWriter();
                OutputWriter outAnswer = new OutputWriter(swAnswer);
                out.printLine(a, b);
                int c = (a - b) >> 1;
                if (a % 2 != b % 2 || a < b || ((c & b) != 0)) {
                    outAnswer.printLine(0);
                } else {
                    int cnt = Integer.bitCount(b);
                    if (c != 0) {
                        outAnswer.printLine(1 << cnt);
                    } else {
                        outAnswer.printLine(((1 << cnt) - 2));
                    }
                }
                tests.add(new Test(sw.toString(), swAnswer.toString()));
            }
        }
        return tests;
    }
}
