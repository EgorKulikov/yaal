package on2012_02.on2012_1_11.taskc;



import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;

import java.util.Collection;
import java.util.Collections;

public class TaskCChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		int count = input.readInt();
		int[] skill = IOUtils.readIntArray(input, count);
		boolean[] used = new boolean[count];
		int first = actual.readInt();
		long firstSkill = 0;
		for (int i = 0; i < first; i++) {
			int index = actual.readInt() - 1;
			if (index < 0 || index >= count || used[index])
				return Verdict.WA;
			firstSkill += skill[index];
			used[index] = true;
		}
		int second = actual.readInt();
		long secondSkill = 0;
		for (int i = 0; i < second; i++) {
			int index = actual.readInt() - 1;
			if (index < 0 || index >= count || used[index])
				return Verdict.WA;
			secondSkill += skill[index];
			used[index] = true;
		}
		if (first + second != count || Math.abs(firstSkill - secondSkill) > CollectionUtils.maxElement(Array.wrap(skill)))
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
