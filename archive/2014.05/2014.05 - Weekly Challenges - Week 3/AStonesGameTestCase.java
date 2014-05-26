package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class AStonesGameTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
			out.printLine(29);
			for (int i = 1; i < 30; i++)
				out.printLine(1 << i);
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
			out.printLine(1);
			for (int i = 5; i <= 5; i++) {
				out.printLine(1 << i);
				int answer = Integer.MAX_VALUE;
				int xor = 0;
				for (int j = 1; j <= (1 << i); j++)
					xor ^= Integer.bitCount(Integer.highestOneBit(j) - 1) + 1;
				for (int j = 1; j <= (1 << i); j++) {
					xor ^= Integer.bitCount(Integer.highestOneBit(j) - 1) + 1;
					for (int k = 0; 2 * k <= j; k++) {
						int current = k == 0 ? 0 : Integer.bitCount(Integer.highestOneBit(k) - 1) + 1;
						if (xor == current) {
							answer = Math.min(answer, j - k);
						}
					}
					xor ^= Integer.bitCount(Integer.highestOneBit(j) - 1) + 1;
				}
				outAnswer.printLine(answer);
			}
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
