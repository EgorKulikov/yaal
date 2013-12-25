package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class DeadPixelsTestCase {
    @TestCase
    public Collection<Test> loadTests() {
        List<Test> tests = new ArrayList<Test>();
        Random random = new Random(239);
        int testCount = 1;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
			out.printLine(20);
			for (int i = 0; i < 20; i++)
				out.printLine(40000, 40000, random.nextInt(100) + 1, random.nextInt(100) + 1, 1000000, random.nextInt(40000), random.nextInt(40000), random.nextInt(99) + 1, random.nextInt(99) + 1, random.nextInt(99) + 1, random.nextInt(99) + 1);
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
			out.printLine(1);
			for (int i = 0; i < 1; i++)
				out.printLine(40000, 40000, 1, 1, 1000000, random.nextInt(40000), random.nextInt(40000), random.nextInt(99) + 1, random.nextInt(99) + 1, random.nextInt(99) + 1, random.nextInt(99) + 1);
			outAnswer.printLine("Case #1:", 40000 * 40000 - 1000000);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
