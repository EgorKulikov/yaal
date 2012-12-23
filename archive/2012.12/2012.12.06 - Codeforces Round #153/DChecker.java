package net.egork;

import net.egork.chelper.checkers.Checker;
import net.egork.chelper.tester.StringInputStream;
import net.egork.chelper.tester.Verdict;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

public class DChecker implements Checker {
	public DChecker(String parameters) {
	}

	public Verdict check(String input, String expectedOutput, String actualOutput) {
		InputReader reader = new InputReader(new StringInputStream(input));
		int count = reader.readInt();
		long[] numbers = IOUtils.readLongArray(reader, count);
		long expectedXOR = 0;
		reader = new InputReader(new StringInputStream(expectedOutput));
		for (int i = 0; i < count; i++) {
			if (reader.readInt() == 2)
				expectedXOR ^= numbers[i];
		}
		long actualXOR = 0;
		reader = new InputReader(new StringInputStream(actualOutput));
		for (int i = 0; i < count; i++) {
			if (reader.readInt() == 2)
				actualXOR ^= numbers[i];
		}
		if (expectedXOR == actualXOR)
			return Verdict.OK;
		return Verdict.WA;
	}
}
