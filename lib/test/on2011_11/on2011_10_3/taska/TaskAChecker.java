package on2011_11.on2011_10_3.taska;


import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;
import net.egork.utils.io.InputReader;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.InputMismatchException;

public class TaskAChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		char[] s = input.readString().toCharArray();
		char[] result;
		try {
			String answer = actual.readString();
			if (!answer.equals(expected.readString()))
				return new Verdict(Verdict.VerdictType.WA, "Different outcome");
			if (answer.equals("NO"))
				return Verdict.OK;
			result = actual.readString().toCharArray();
		} catch (InputMismatchException e) {
			return new Verdict(Verdict.VerdictType.PE, null);
		}
		for (int i = 2; i <= result.length; i++) {
			boolean prime = true;
			for (int j = 2; j * j <= i && prime; j++) {
				if (i % j == 0)
					prime = false;
			}
			if (!prime)
				continue;
			for (int j = 1; j * i <= result.length; j++) {
				if (result[i - 1] != result[j * i - 1])
					return new Verdict(Verdict.VerdictType.WA, i + " not equals " + (i * j));
			}
		}
		Arrays.sort(result);
		Arrays.sort(s);
		if (!Arrays.equals(s, result))
			return new Verdict(Verdict.VerdictType.WA, "Not permutation");
		return Verdict.OK;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		return Collections.emptyList();
	}
}
