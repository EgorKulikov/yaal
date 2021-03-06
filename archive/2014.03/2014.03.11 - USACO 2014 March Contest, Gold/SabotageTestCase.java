package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.TestCase;

import net.egork.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class SabotageTestCase {
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
        int testCount = 10000;
		int size = 100;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			int[] production = new int[size];
			for (int i = 0; i < production.length; i++)
				production[i] = random.nextInt(10000);
			out.printLine(production.length);
			out.printLine(production);
			double minAverage = Double.POSITIVE_INFINITY;
			long sum = 0;
			for (int i = 0; i < production.length; i++) {
				sum += production[i];
				long current = sum;
				for (int j = production.length - 1; j > i + 1; j--) {
					current += production[j];
					minAverage = Math.min(minAverage, (double)current / (i + 1 + production.length - j));
				}
			}
			outAnswer.printFormat("%.3f\n", minAverage);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
