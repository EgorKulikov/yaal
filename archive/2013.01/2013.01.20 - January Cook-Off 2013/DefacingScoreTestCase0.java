package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import net.egork.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class DefacingScoreTestCase0 implements TestProvider {
    public Collection<Test> createTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
			out.printLine(201301);
			for (int i = 0; i < 201301; i++) {
				int a = random.nextInt(20130121);
				int b = random.nextInt(20130121);
				if (a > b) {
					int t = a;
					a = b;
					b = t;
				}
				out.printLine(a, b);
			}
            tests.add(new Test(sw.toString()));
        }
        return tests;
    }
}
