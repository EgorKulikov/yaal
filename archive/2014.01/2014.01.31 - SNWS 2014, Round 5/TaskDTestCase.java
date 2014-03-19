package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

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
        int testCount = 100;
        for (int testNumber = 0; testNumber < testCount; testNumber++) {
            StringWriter sw = new StringWriter();
            OutputWriter out = new OutputWriter(sw);
            StringWriter swAnswer = new StringWriter();
            OutputWriter outAnswer = new OutputWriter(swAnswer);
			int size = random.nextInt(20) + 1;
			int[] array = new int[size];
			for (int i = 0; i < size; i++)
				array[i] = random.nextInt((int) (1e9 + 1));
			int mod = random.nextInt((int) 1e9) + 1;
			out.printLine(size, mod);
			out.printLine(array);
			int answer = 0;
			int[] sequence = new int[size];
			int max = 0;
			for (int i = 0; i < (1 << size); i++) {
				int at = 0;
				for (int j = 0; j < size; j++) {
					if ((i >> j & 1) == 1)
						sequence[at++] = array[j];
				}
				if (at < max)
					continue;
				boolean good = true;
				for (int j = 1; j < at; j++) {
					if (sequence[j - 1] >= sequence[j]) {
						good = false;
						break;
					}
				}
				if (good) {
					if (at == max)
						answer++;
					else {
						max = at;
						answer = 1;
					}
				}
			}
			outAnswer.printLine(answer % mod);
            tests.add(new Test(sw.toString(), swAnswer.toString()));
        }
        return tests;
    }
}
