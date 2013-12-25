package on2011_11.on2011_10_28.task1711;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Task1711Checker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		if (expected.readString().equals("IMPOSSIBLE")) {
			if (actual.readString().equals("IMPOSSIBLE"))
				return Verdict.OK;
			else
				return new Verdict(Verdict.VerdictType.WA, "IMPOSSIBLE expected");
		}
		int count = input.readInt();
		String[] answer = IOUtils.readStringArray(actual, count);
		String[][] variants = IOUtils.readStringTable(input, count, 3);
		int[] order = IOUtils.readIntArray(input, count);
		int index = 0;
		for (int i : order) {
			if (!Arrays.asList(variants[i - 1]).contains(answer[index++]))
				return new Verdict(Verdict.VerdictType.WA, "Not in list");
		}
		String[] answerCopy = answer.clone();
		Arrays.sort(answerCopy);
		if (!Arrays.deepEquals(answerCopy, answer))
			return new Verdict(Verdict.VerdictType.WA, "Not sorted");
		return Verdict.OK;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		return Collections.emptyList();
	}
}
