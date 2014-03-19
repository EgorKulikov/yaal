package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class TaskBTestCase {
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
        int testCount = 100;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			int length = random.nextInt(20) + 1;
			int[] masks = new int[3];
			for (int i = 0; i < 3; i++)
				masks[i] = random.nextInt(1 << length);
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < length; j++)
					out.print(masks[i] >> j & 1);
				out.printLine();
			}
			int answer = 0;
			for (int i = 0; i < (1 << length); i++) {
				int value = Integer.bitCount(i ^ masks[0]);
				boolean good = true;
				for (int j = 1; j < 3; j++) {
					if (value != Integer.bitCount(i ^ masks[j]))
						good = false;
				}
				if (good)
					answer++;
			}
			outAnswer.printLine(answer);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
