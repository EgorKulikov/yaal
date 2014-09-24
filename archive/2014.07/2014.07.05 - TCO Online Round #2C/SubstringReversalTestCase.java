package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class SubstringReversalTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		StringBuilder input = new StringBuilder(2500);
		input.append('b');
		for (int i = 0; i < 2499; i++) {
			input.append('a');
		}
		tests.add(createTest(new int[]{0, 2499}, input.toString()));
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
