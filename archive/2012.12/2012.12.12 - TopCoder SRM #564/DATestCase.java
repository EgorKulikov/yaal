package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TopCoderTestProvider;

import java.util.*;

public class DATestCase implements TopCoderTestProvider {
	public Collection<NewTopCoderTest> createTests() {
		Random random = new Random(239);
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		for (int i = 0; i < 1000; i++) {
			int count = random.nextInt(10) + 1;
			int[] cards = new int[count];
			for (int j = 0; j < count; j++)
				cards[j] = random.nextInt(1024);
			int n = random.nextInt(1024);
			long[] current = new long[1024];
			long[] next = new long[1024];
			current[0] = 1;
			for (int j : cards) {
				Arrays.fill(next, 0);
				for (int k = 0; k < 1024; k++) {
					for (int l = 0; l <= j; l++)
						next[k ^ l] += current[k];
				}
				for (int k = 0; k < 1024; k++)
					current[k] = next[k] % ((long) (1e9 + 7));
			}
			tests.add(new NewTopCoderTest(new Object[]{cards, n}, (int)current[n]));
		}
		return tests;
	}
}
