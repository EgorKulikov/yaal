package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class JanuszTheBusinessmanTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		int n = 10;
		Random random = new Random(239);
		for (int i = 0; i < 0; i++) {
			int[] arrival = new int[n];
			int[] departure = new int[n];
			for (int j = 0; j < n; j++) {
				int a = random.nextInt(365) + 1;
				int b = random.nextInt(365) + 1;
				arrival[j] = Math.min(a, b);
				departure[j] = Math.max(a, b);
			}
			int answer = n;
			for (int j = 0; j < (1 << n); j++) {
				boolean ok = true;
				for (int k = 0; k < n; k++) {
					boolean found = false;
					for (int l = 0; l < n; l++) {
						if ((j >> l & 1) == 1 && arrival[l] <= departure[k] && departure[l] >= arrival[k]) {
							found = true;
							break;
						}
					}
					if (!found) {
						ok = false;
						break;
					}
				}
				if (ok) answer = Math.min(answer, Integer.bitCount(j));
			}
			tests.add(createTest(answer, arrival, departure));
		}
		return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
