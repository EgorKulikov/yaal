package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.TestCase;

import net.egork.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class LazyTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
			out.printLine(100000, random.nextInt(2000000) + 1);
			for (int i = 0; i < 100000; i++)
				out.printLine(random.nextInt(10000) + 1, random.nextInt(1000001), random.nextInt(1000001));
            tests.add(new Test(sw.toString()));
        }
        return tests;
    }

    @TestCase
    public Collection<Test> accuracyTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1000;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			int count = 20;
			int[] amount = new int[count];
			int[] x = new int[count];
			int[] y = new int[count];
			for (int i = 0; i < count; i++) {
				amount[i] = random.nextInt(10000) + 1;
				x[i] = random.nextInt(count + 1);
				y[i] = random.nextInt(count + 1);
			}
			int side = random.nextInt(count) + 1;
			out.printLine(count, side);
			for (int i = 0; i < count; i++)
				out.printLine(amount[i], x[i], y[i]);
			int answer = 0;
			for (int i = 0; i <= 2 * count; i++) {
				for (int j = 0; j <= 2 * count; j++) {
					int current = 0;
					for (int k = 0; k < count; k++) {
						if (Math.abs(x[k] * 2 - i) + Math.abs(y[k] * 2 - j) <= 2 * side)
							current += amount[k];
					}
					answer = Math.max(answer, current);
				}
			}
			outAnswer.printLine(answer);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
