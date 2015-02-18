package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class ChocolateDividingEasyTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
		List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
		String[] chocolate = new String[50];
		Random random = new Random(239);
		for (int i = 0; i < 50; i++) {
			StringBuilder builder = new StringBuilder();
			for (int j = 0; j < 50; j++) {
				builder.append((char)('0' + random.nextInt(10)));
			}
			chocolate[i] = builder.toString();
		}
		tests.add(createTest(null, (Object)chocolate));
        return tests;
    }

	private NewTopCoderTest createTest(Object answer, Object...arguments) {
		return new NewTopCoderTest(arguments, answer);
	}
}
