package on2011_11.on2011_10_29.task1794;



import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;

import java.util.Collection;
import java.util.Collections;

public class Task1794Checker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		int good = expected.readInt() - 1;
		int answer = actual.readInt() - 1;
		int count = input.readInt();
		if (answer < 0 || answer >= count)
			return new Verdict(Verdict.VerdictType.WA, "not a valid position");
		int[] preference = IOUtils.readIntArray(input,  count);
		int current = 1;
		int expectedMatch = 0;
		int actualMatch = 0;
		for (int i = 0; i < count; i++) {
			if (preference[(i + good) % count] == current)
				expectedMatch++;
			if (preference[(i + answer) % count] == current)
				actualMatch++;
			current++;
		}
		if (expectedMatch > actualMatch)
			return new Verdict(Verdict.VerdictType.WA, "less matches");
		if (expectedMatch < actualMatch)
			return new Verdict(Verdict.VerdictType.WA, "more matches!!!");
		return Verdict.OK;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		return Collections.emptyList();
	}
}
