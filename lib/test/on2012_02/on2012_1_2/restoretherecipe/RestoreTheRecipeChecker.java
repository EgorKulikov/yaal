package on2012_02.on2012_1_2.restoretherecipe;



import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;

import java.util.Collection;
import java.util.Collections;

public class RestoreTheRecipeChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		if (expected.readInt() == -1) {
			if (actual.readInt() == -1)
				return Verdict.OK;
			return Verdict.WA;
		}
		int count = input.readInt();
		int restrictionCount = input.readInt();
		long[] answer = IOUtils.readLongArray(actual, count);
		for (int i = 0; i < restrictionCount; i++) {
			int from = input.readInt() - 1;
			int to = input.readInt() - 1;
			long sum = 0;
			for (int j = from; j <= to; j++)
				sum += answer[j];
			if (sum != input.readInt())
				return Verdict.WA;
		}
		return Verdict.OK;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		return Collections.emptyList();
	}
}
