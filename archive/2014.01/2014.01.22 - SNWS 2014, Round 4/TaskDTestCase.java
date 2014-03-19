package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.*;

public class TaskDTestCase {
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
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 100; i++)
			list.add(i);
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			int count = random.nextInt(10) + 1;
			Collections.shuffle(list, random);
			int[] deltas = new int[count];
			for (int i = 0; i < count; i++)
				deltas[i] = list.get(i);
			int[] answer = new int[20001];
			Arrays.fill(answer, Integer.MAX_VALUE);
			answer[0] = 0;
			for (int i : deltas) {
				for (int j = 0; j <= 20000 - i; j++) {
					if (answer[j] != Integer.MAX_VALUE)
						answer[j + i] = Math.min(answer[j + i], answer[j] + 1);
				}
			}
			out.printLine(10001, count);
			for (int i = 0; i < count; i++)
				out.printLine(deltas[i]);
			for (int i = 10000; i <= 20000; i++) {
				out.printLine(i);
				if (answer[i] == Integer.MAX_VALUE)
					outAnswer.printLine(-1);
				else
					outAnswer.printLine(answer[i]);
			}
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
