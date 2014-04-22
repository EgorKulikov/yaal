package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class TaskCTestCase {
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
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		StringWriter swAnswer = new StringWriter();
		OutputWriter outAnswer = new OutputWriter(swAnswer);
		int total = 0;
		for (int i = 1; i <= 40; i++) {
			for (int j = 1; j <= 40; j++) {
				total += i * j;
			}
		}
		out.printLine(total);
		for (int i = 1; i <= 40; i++) {
			for (int j = 1; j <= 40; j++) {
				for (int k = 0; k < i * j; k++)
					out.printLine(i, j, k);
			}
		}
		tests.add(new Test(sw.toString(), swAnswer.toString()));
        return tests;
    }
}
