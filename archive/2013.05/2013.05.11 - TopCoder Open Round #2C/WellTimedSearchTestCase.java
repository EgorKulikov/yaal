package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import net.egork.misc.ArrayUtils;

import java.util.*;

public class WellTimedSearchTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		for (int i = 1; i <= 30; i++) {
			for (int j = 1; j <= i; j++) {
				for (int k = j; k <= i; k++) {
					double[][] result = new double[i + 1][k];
					ArrayUtils.fill(result, -1);
					tests.add(createTest(go(i, 0, j - 1, k - 1, result), i, j, k));
				}
			}
		}
        return tests;
    }

	private double go(int n, int step, int a, int b, double[][] result) {
		if (step > b)
			return 0;
		if (result[n][step] != -1)
			return result[n][step];
		result[n][step] = 0;
		for (int i = 0; i < n; i++) {
			double current = 0;
			if (step >= a)
				current += 1d / n;
			if (i != 0)
				current += i * go(i, step + 1, a, b, result) / n;
			if (i != n - 1)
				current += (n - 1 - i) * go(n - 1 - i, step + 1, a, b, result) / n;
			result[n][step] = Math.max(result[n][step], current);
		}
		return result[n][step];
	}

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
