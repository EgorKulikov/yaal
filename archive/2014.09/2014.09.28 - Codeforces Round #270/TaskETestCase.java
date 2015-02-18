package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.TestCase;

import net.egork.collections.intcollection.IntArray;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.OutputWriter;

import java.util.*;
import java.io.StringWriter;

public class TaskETestCase {
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
        int testCount = 0;
		int[] order = ArrayUtils.createOrder(6);
		for (int i = 0; i < 6; i++) {
			order[i]++;
		}
		int[] target = order.clone();
        while (new IntArray(order).nextPermutation()) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			out.printLine(2, 3);
			out.printLine(order);
			out.printLine(target);
			outAnswer.printLine(0);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
