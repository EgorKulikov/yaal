package net.egork;

import net.egork.chelper.checkers.Checker;
import net.egork.chelper.tester.Verdict;
import net.egork.collections.set.EHashSet;
import net.egork.utils.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.Set;


public class TaskCChecker implements Checker {
    public TaskCChecker(String parameters) {
    }

    public Verdict check(String input, String expectedOutput, String actualOutput) {
        InputReader in = new InputReader(new StringBufferInputStream(input));
        InputReader expected;
        if (expectedOutput == null)
            expected = null;
        else
            expected = new InputReader(new StringBufferInputStream(expectedOutput));
        InputReader actual = new InputReader(new StringBufferInputStream(actualOutput));
        return check(in, expected, actual);
    }

    public Verdict check(InputReader in, InputReader expected, InputReader actual) {
		if (expected == null)
			return Verdict.UNDECIDED;
		int size = expected.readInt();
		if (size != actual.readInt())
			return Verdict.WA;
		Set<String> expectedSet = new EHashSet<String>();
		for (int i = 0; i < size; i++)
			expectedSet.add(expected.readString());
		Set<String> actualSet = new EHashSet<String>();
		for (int i = 0; i < size; i++)
			actualSet.add(actual.readString());
		if (expectedSet.equals(actualSet))
			return Verdict.OK;
        return Verdict.WA;
    }
}
