package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		boolean[] present = new boolean[300001];
		boolean[] made = new boolean[300001];
		for (int i : numbers) {
			if (present[i])
				made[i] = true;
			present[i] = true;
		}
		for (int i = 300000; i > 0; i--) {
			if (!present[i] && !made[i])
				continue;
			for (int j : numbers) {
				if (i > j || i == j && made[i])
					made[i % j] = true;
			}
		}
		int min = ArrayUtils.minElement(numbers);
		int answer = 0;
		for (int i = 0; i < min; i++) {
			if (present[i] || made[i])
				answer++;
		}
		if (ArrayUtils.count(numbers, min) == 1)
			answer++;
		out.printLine(answer);
    }
}
