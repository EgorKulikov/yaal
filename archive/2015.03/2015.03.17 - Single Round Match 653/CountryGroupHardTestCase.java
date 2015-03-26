package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import net.egork.collections.intcollection.IntArray;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.map.Counter;

import java.util.*;

public class CountryGroupHardTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		for (int N = 1; N <= 8; N++) {
			Counter<IntList> counter = new Counter<>();
			int[] current = new int[N];
			int[] target = new int[N];
			build(0, current, target, counter);
			for (Map.Entry<IntList, Long> entry : counter.entrySet()) {
				tests.add(createTest(entry.getValue() > 1 ? "Insufficient" : "Sufficient", (Object) entry.getKey().toArray()));
			}
		}
		return tests;
    }

	private void build(int step, int[] current, int[] target, Counter<IntList> counter) {
		if (step == current.length) {
			for (int i = 0; i < 1 << step; i++) {
				for (int j = 0; j < step; j++) {
					if ((i >> j & 1) == 0) {
						target[j] = 0;
					} else {
						target[j] = current[j];
					}
				}
				counter.add(new IntArray(target.clone()));
			}
			return;
		}
		for (int i = 1; step + i <= current.length; i++) {
			Arrays.fill(current, step, step + i, i);
			build(step + i, current, target, counter);
		}
	}

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
