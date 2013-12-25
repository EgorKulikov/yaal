package on2011_11.on2011_10_29.task1727;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Task1727Checker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		int number = input.readInt();
		int count = actual.readInt();
		int[] array = IOUtils.readIntArray(actual, count);
		Set<Integer> was = new HashSet<Integer>();
		for (int i : array) {
			if (i <= 0 || i >= 100000)
				return new Verdict(Verdict.VerdictType.WA, "Invalid number");
			if (was.contains(i))
				return new Verdict(Verdict.VerdictType.WA, "Duplicate number");
			was.add(i);
			number -= i;
		}
		if (number != 0)
			return Verdict.WA;
		return Verdict.OK;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		return Collections.emptyList();
	}
}
