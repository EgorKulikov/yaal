package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import net.egork.misc.ArrayUtils;

import java.util.*;

public class SubdividedSlimesTestCase {
	int[][] answer = new int[100][100];

    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		ArrayUtils.fill(answer, -1);
		for (int i = 1; i < 100; i++) {
			for (int j = 0; j < i; j++) {
				calculate(i, j);
			}
		}
		int n = 100;
		for (int i = 2; i < n; i++) {
			int at = 0;
			for (int j = 1; j <= n * n; j++) {
				while (at < i && answer[i][at] < j) {
					at++;
				}
				tests.add(createTest(at == i ? -1 : at, i, j));
			}
		}
		return tests;
    }

	private int calculate(int size, int parts) {
		if (answer[size][parts] != -1) {
			return answer[size][parts];
		}
		answer[size][parts] = 0;
		for (int i = 1; i < size; i++) {
			for (int j = 0; j <= parts - 1; j++) {
				answer[size][parts] = Math.max(answer[size][parts], calculate(i, j) + calculate(size - i, parts - j - 1) + i * (size - i));
			}
		}
		return answer[size][parts];
	}

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
