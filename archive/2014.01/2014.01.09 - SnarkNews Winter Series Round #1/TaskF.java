package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		int[] digits = new int[30];
		IntList answer = new IntArrayList();
		for (int i = 2; i * i <= number; i++) {
			int current = number;
			int size = 0;
			while (current != 0) {
				digits[size++] = current % i;
				current /= i;
			}
			boolean good = true;
			for (int j = 0, k = size - 1; j < k; j++, k--) {
				if (digits[j] != digits[k]) {
					good = false;
					break;
				}
			}
			if (good)
				answer.add(i);
		}
		for (int i = 1; i * (i + 1) < number; i++) {
			if (number % i == 0)
				answer.add(number / i - 1);
		}
		answer.inPlaceSort();
		out.printLine(answer);
    }
}
