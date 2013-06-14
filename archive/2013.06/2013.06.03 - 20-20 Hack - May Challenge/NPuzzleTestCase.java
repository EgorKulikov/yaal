package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.collections.sequence.Array;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.*;

public class NPuzzleTestCase {
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
        int testCount = 10;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			int[] order = ArrayUtils.createOrder(25);
			Collections.shuffle(Array.wrap(order), random);
			out.printLine(5);
			out.printLine(order);
			tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
