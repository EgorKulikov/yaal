package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class OrderOfOperationsTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		int n = 50;
		int m = 20;
		String[] input = new String[n];
		for (int i = 0; i < n; i++) {
			StringBuilder current = new StringBuilder(m);
			for (int j = 0; j < m; j++) {
				if (i % m == j) {
					current.append(1);
				} else {
					current.append(0);
				}
			}
			input[i] = current.toString();
		}
		tests.add(createTest(20, (Object)input));
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
