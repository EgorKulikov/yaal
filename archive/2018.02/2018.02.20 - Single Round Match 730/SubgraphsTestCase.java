package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class SubgraphsTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
        for (int k = 2; k <= 23; k++) {
            tests.add(createTest(null, k));
        }
        return tests;
    }

    private NewTopCoderTest createTest(Object answer, Object...arguments) {
        return new NewTopCoderTest(arguments, answer);
    }
}
