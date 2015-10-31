package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import net.egork.collections.comparators.IntComparator;
import net.egork.misc.ArrayUtils;

import java.util.*;

public class ModModModTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		Random random = new Random(239);
		int[] mod = new int[5000];
		for (int i = 0; i < 5000; i++) {
			mod[i] = random.nextInt(10000000) + 1;
		}
		ArrayUtils.sort(mod, IntComparator.REVERSE);
		tests.add(createTest(null, mod, 10000000));
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
