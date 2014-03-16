package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class AlienAndSetDiv1TestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		for (int i = 1; i <= 10; i++) {
			int[] result = new int[11];
			int[] first = new int[i];
			int[] second = new int[i];
			for (int j = 0; j < (1 << (2 * i)); j++) {
				if (Integer.bitCount(j) != i)
					continue;
				int fs = 0;
				int ss = 0;
				for (int k = 0; k < 2 * i; k++) {
					if ((j >> k & 1) == 0)
						first[fs++] = k;
					else
						second[ss++] = k;
				}
				int min = Integer.MAX_VALUE;
				for (int k = 0; k < i; k++)
					min = Math.min(min, Math.abs(first[k] - second[k]));
				for (int k = 1; k <= min; k++)
					result[k]++;
			}
			for (int j = 1; j <= 10; j++)
				tests.add(createTest(result[j], i, j));
		}
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
