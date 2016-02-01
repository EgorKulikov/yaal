package net.egork;

import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.generated.collections.set.IntSet;

public class TreeAndPathLength3 {
	public int[] construct(int s) {
		IntSet set = new IntHashSet();
		for (int a = 1; a <= 100; a++) {
			for (int b = 1; b <= 100; b++) {
				for (int c = 1; c <= 100; c++) {
					for (int d = 1; d <= 100; d++) {
						set.add(a * b + b * c + c * d);
					}
				}
			}
		}

		for (int i = 3; i <= 10000; i++) {
			if (!set.contains(i)) {
				System.err.println(i);
			}
		}

		if (s == 1) {
			return new int[]{0, 1, 1, 2, 2, 3};
		}
		int k = 2;
		while ((k + 1) * k <= s) {
			k++;
		}
		IntList answer = new IntArrayList();
		for (int i = 0; i < k; i++) {
			answer.add(0);
			answer.add(2 * i + 1);
			answer.add(2 * i + 1);
			answer.add(2 * i + 2);
		}
		for (int j = 0; j < s - k * (k - 1); j++) {
			answer.add(2 * k + j);
			answer.add(2 * k + j + 1);
		}
		if (answer.size() > 499 * 2) {
			throw new RuntimeException();
		}
		return answer.toArray();
	}
}
