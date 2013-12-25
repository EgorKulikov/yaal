package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class PhotoTestCase {
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
        int testCount = 50;
		int n = 20;
		int m = 10;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			int photoCount = random.nextInt(m) + 1;
			out.printLine(n, photoCount);
			int[] from = new int[photoCount];
			int[] to = new int[photoCount];
			for (int i = 0; i < photoCount; i++) {
				int a = random.nextInt(n);
				int b = random.nextInt(n);
				if (a > b) {
					int t = a;
					a = b;
					b = t;
				}
				from[i] = a;
				to[i] = b;
				out.printLine(a + 1, b + 1);
			}
			int answer = -1;
			for (int i = 0; i < (1 << n); i++) {
				if (Integer.bitCount(i) <= answer)
					continue;
				boolean valid = true;
				for (int j = 0; j < photoCount; j++) {
					int mask = (1 << (to[j] + 1)) - (1 << from[j]);
					if (Integer.bitCount(mask & i) != 1) {
						valid = false;
						break;
					}
				}
				if (valid)
					answer = Integer.bitCount(i);
			}
			outAnswer.printLine(answer);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
