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
        int testCount = 0;
        for (int zero = 0; zero < 5; zero++) {
			for (int one = 0; one < 5; one++) {
				for (int target = 0; target < 2; target++) {
					if (zero + one == 0)
						continue;
					StringWriter sw = new StringWriter();
					OutputWriter out = new OutputWriter(sw);
					StringWriter swAnswer = new StringWriter();
					OutputWriter outAnswer = new OutputWriter(swAnswer);
					int answer = 0;
					for (int i = 0; i < (1 << (zero + one)); i++) {
						if (Integer.bitCount(i) != one)
							continue;
						int value = i;
						for (int j = 1; j < zero + one; j++) {
							int current = value & 3;
							value >>= 2;
							value <<= 1;
							if (current == 0)
								value++;
						}
						if (value == target)
							answer++;
					}
					out.printLine(zero, one, target);
					outAnswer.printLine(answer);
					tests.add(new Test(sw.toString(), swAnswer.toString()));
				}
			}
        }
        return tests;
    }
}
