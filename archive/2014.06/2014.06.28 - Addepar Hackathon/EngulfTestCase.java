package net.egork;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class EngulfTestCase {
	@TestCase
	public Collection<Test> loadTests() {
		List<Test> tests = new ArrayList<Test>();
		Random random = new Random(239);
		tests.add(getTest(random, 50, 10, 1, 1));
		tests.add(getTest(random, 200, 15, 5, 3, 2));
		tests.add(getTest(random, 2000, 20, 4, 3, 2, 1));
		tests.add(getTest(random, 2000, 20, 1, 1, 1, 1, 1, 1));
		return tests;
	}

	private Test getTest(Random random, int height, int width, int... ratio) {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		out.printLine(height, width, ratio.length);
		int sum = (int) ArrayUtils.sumArray(ratio);
		for (int i = 0; i < height * width; i++) {
			int key = random.nextInt(sum);
			for (int j = 0; j < ratio.length; j++) {
				if (key < ratio[j]) {
					out.printLine(j + 1);
					break;
				}
				key -= ratio[j];
			}
		}
		return new Test(sw.toString());

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
