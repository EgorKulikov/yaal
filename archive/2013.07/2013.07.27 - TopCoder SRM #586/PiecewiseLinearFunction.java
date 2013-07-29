package net.egork;

import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntSet;

public class PiecewiseLinearFunction {
    public int maximumSolutions(int[] Y) {
		int count = Y.length;
		for (int i = 0; i < count; i++)
			Y[i] *= 2;
		for (int i = 1; i < count; i++) {
			if (Y[i] == Y[i - 1])
				return -1;
		}
		IntSet variants = new IntHashSet();
		for (int i : Y) {
			variants.add(i);
			variants.add(i - 1);
			variants.add(i + 1);
		}
		int answer = 0;
		for (int i : variants.toArray()) {
			int current = 0;
			if (Y[0] == i)
				current++;
			for (int j = 1; j < count; j++) {
				if (Y[j] == i)
					current++;
				if (Y[j] > i && Y[j - 1] < i || Y[j] < i && Y[j - 1] > i)
					current++;
			}
			answer = Math.max(answer, current);
		}
		return answer;
    }
}
