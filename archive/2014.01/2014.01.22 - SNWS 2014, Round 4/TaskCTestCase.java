package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.numbers.IntegerUtils;
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
		for (int i = 1; i <= 50; i++) {
			int[] answer = new int[2 * i + 2];
			for (int j = -i; j <= i; j++) {
				for (int k = 1; k <= i; k++) {
					if (IntegerUtils.gcd(j, k) != 1)
						continue;
					answer[(i / Math.max(Math.abs(j), k)) * 2 + 1]++;
				}
			}
			answer[1] = -1;
			for (int j = 1; j <= 2 * i + 1; j++) {
				StringWriter sw = new StringWriter();
				OutputWriter out = new OutputWriter(sw);
				StringWriter swAnswer = new StringWriter();
				OutputWriter outAnswer = new OutputWriter(swAnswer);
				out.printLine(i, j);
				outAnswer.printLine(answer[j]);
				tests.add(new Test(sw.toString(), swAnswer.toString()));
			}
		}
        return tests;
    }
}
