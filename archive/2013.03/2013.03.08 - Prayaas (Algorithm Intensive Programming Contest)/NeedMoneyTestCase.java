package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.TestCase;

import net.egork.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class NeedMoneyTestCase {
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
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			int numTests = 100000;
			out.printLine(numTests);
			for (int i = 0; i < numTests; i++) {
				int count = random.nextInt(8) + 2;
				long a = random.nextInt(2000001) - 1000000;
				long b = random.nextInt(2000001) - 1000000;
				long c = random.nextInt(2000001) - 1000000;
				long m = random.nextInt(2000001) - 1000000;
				long k = random.nextInt(2000001) - 1000000;
				out.printLine(m, count, a, b, c, k);
				long answer = Long.MAX_VALUE;
				for (int j = 0; j < (1 << (count + 2)); j += 4) {
					long current = 0;
					for (int l = 2; l < count + 2; l++)
						current += (a * (2 * (j >> l & 1) - 1) + b * (2 * (j >> (l - 1) & 1) - 1) + c * (2 * (j >> (l - 2) & 1) - 1)) * k;
					if (current >= m)
						answer = Math.min(answer, current);
				}
				if (answer < m)
					throw new RuntimeException();
				if (answer == Long.MAX_VALUE)
					outAnswer.printLine("Poor Vicky");
				else
					outAnswer.printLine(answer);
			}
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
