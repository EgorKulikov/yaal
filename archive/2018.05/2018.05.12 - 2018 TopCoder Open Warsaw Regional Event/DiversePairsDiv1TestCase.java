package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class DiversePairsDiv1TestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
        for (int i = 0; i <= 100; i++) {
            tests.add(createTest(null, 1, i + 1));
        }
        return tests;
    }

    private NewTopCoderTest createTest(Object answer, Object...arguments) {
        return new NewTopCoderTest(arguments, answer);
    }
}
