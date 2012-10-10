package on2012_03.on2012_2_12.taskb;



import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;

import java.util.Collection;
import java.util.Collections;

public class TaskBChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		double answer = expected.readDouble();
		if (actual.readDouble() != answer)
			return Verdict.WA;
		int count = input.readInt();
		int basketCount = input.readInt();
		int[] price = new int[count];
		int[] type = new int[count];
		IOUtils.readIntArrays(input, price, type);
		boolean[] used = new boolean[count];
		for (int i = 0; i < basketCount; i++) {
			int inBasket = actual.readInt();
			int min = Integer.MAX_VALUE;
			count -= inBasket;
			boolean hasChair = false;
			for (int j = 0; j < inBasket; j++) {
				int index = actual.readInt() - 1;
				if (used[index])
					return Verdict.WA;
				used[index] = true;
				min = Math.min(min, price[index]);
				answer -= price[index];
				if (type[index] == 1)
					hasChair = true;
			}
			if (hasChair)
				answer += min / 2.;
		}
		if (count != 0 || answer != 0)
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
