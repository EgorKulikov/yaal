package net.egork;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class CommonPalindromicSubsequencesTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> tests = new ArrayList<NewTopCoderTest>();
        Random random = new Random(239);
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < 60; i++) {
            a.append(random.nextBoolean() ? 'a' : 'b');
        }
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < 60; i++) {
            b.append(random.nextBoolean() ? 'a' : 'b');
        }
        tests.add(createTest(null, a.toString(), b.toString()));
        return tests;
    }

    private NewTopCoderTest createTest(Object answer, Object...arguments) {
        return new NewTopCoderTest(arguments, answer);
    }
}
