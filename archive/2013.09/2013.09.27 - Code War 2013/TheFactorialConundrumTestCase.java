package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class TheFactorialConundrumTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
			StringWriter swAnswer = new StringWriter();
			OutputWriter outAnswer = new OutputWriter(swAnswer);
			out.printLine(99);
			for (int i = 2; i <= 100; i++) {
				out.printLine(i);
				long[] factorial = IntegerUtils.generateFactorial(i + 1, i);
				long current = 1;
				for (int j = 1; ; j++) {
					if (current == 0) {
						outAnswer.printLine(j - 2);
						break;
					}
					current = current * factorial[j] % i;
				}
			}
            tests.add(new Test(sw.toString(), swAnswer.toString()));
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
			out.printLine(10);
			for (int i = 0; i < 10; i++) {
				BigInteger p = BigInteger.valueOf((long) (1e9 + random.nextInt((int) 1e9))).nextProbablePrime();
				BigInteger q = BigInteger.valueOf((long) (1e9 + random.nextInt((int) 1e9))).nextProbablePrime();
				out.printLine(p.multiply(q));
				outAnswer.printLine(p.max(q).subtract(BigInteger.ONE));
			}
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
