package on2011_10.on2011_9_30.swords;


import net.egork.chelper.task.Test;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.InputMismatchException;

public class SwordsChecker {
	public String check(InputReader input, InputReader expected, InputReader actual) {
		int count = input.readInt();
		int delta = input.readInt();
		int[] age = IOUtils.readIntArray(input, count);
		if (expected.readInt() == -1) {
			if (actual.readInt() != -1)
				return "Find solution when no solution";
			try {
				actual.readString();
				return "More tokens after end";
			} catch (InputMismatchException e) {
				return null;
			}
		}
		int[] answer;
		try {
			answer = IOUtils.readIntArray(actual, count);
		} catch (InputMismatchException e) {
			return "Answer is too short";
		}
		try {
			input.readString();
			return "Answer is too long";
		} catch (InputMismatchException ignored) {
		}
		int[] remaining = new int[count + 1];
		Arrays.fill(remaining, 2);
		remaining[0] = 1;
		for (int i = 0; i < count; i++) {
			remaining[answer[i]]--;
			if (remaining[answer[i]] < 0)
				return "Too much of same type";
			if (answer[i] != 0 && age[answer[i] - 1] - age[i] < delta)
				return "Too close";
		}
		return null;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		return Collections.emptyList();
	}
}
