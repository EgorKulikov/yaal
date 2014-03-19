package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class CombinationLockDiv1TestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		String[] s = new String[50];
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 50; i++)
			builder.append('0');
		Arrays.fill(s, builder.toString());
		String[] t = new String[50];
		builder = new StringBuilder();
		for (int i = 0; i < 50; i++)
			builder.append((char)('0' + i * 4 % 10));
		Arrays.fill(t, 0, 25, builder.toString());
		builder = new StringBuilder();
		for (int i = 0; i < 50; i++)
			builder.append((char)('0' + (49 * 4 - i * 4) % 10));
		Arrays.fill(t, 25, 50, builder.toString());
		tests.add(createTest(1249 * 4, s, t));
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
