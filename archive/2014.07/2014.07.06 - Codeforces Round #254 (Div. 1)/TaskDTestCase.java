package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.TestCase;

import net.egork.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class TaskDTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < 500; i++) {
				s.append((char)('a' + random.nextInt(26)));
			}
			StringBuilder t = new StringBuilder();
			for (int i = 0; i < 100; i++) {
				t.append(s);
			}
			out.printLine(t);
			out.printLine(100000);
			for (int i = 0; i < 100000; i++) {
				int firstStart = random.nextInt(s.length());
				int secondStart = random.nextInt(s.length());
				out.printLine(s.substring(firstStart, Math.min(s.length(), firstStart + random.nextInt(4) + 1)),
					s.substring(secondStart, Math.min(s.length(), secondStart + random.nextInt(4) + 1)));
			}
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
