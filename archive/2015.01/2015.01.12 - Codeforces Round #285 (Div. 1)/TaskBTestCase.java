package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.TestCase;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class TaskBTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
			int[] p = ArrayUtils.createOrder(200000);
			int[] q = ArrayUtils.createOrder(200000);
			for (int i = 1; i < p.length; i++) {
				int at = random.nextInt(i + 1);
				int temp = p[at];
				p[at] = p[i];
				p[i] = temp;
				at = random.nextInt(i + 1);
				temp = q[at];
				q[at] = q[i];
				q[i] = temp;
			}
			out.printLine(p.length);
			out.printLine(p);
			out.printLine(q);
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
