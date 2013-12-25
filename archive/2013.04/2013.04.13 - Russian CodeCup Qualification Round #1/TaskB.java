package net.egork;

import net.egork.collections.map.Counter;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] lengths = IOUtils.readIntArray(in, count);
		long answer = 0;
		Arrays.sort(lengths);
		Counter<Integer> counter = new Counter<Integer>();
		for (int i : lengths)
			counter.add(i);
		int[] pairs = new int[count];
		int last = lengths[count - 1];
		int lastCount = 1;
		for (int i = count - 2; i >= 0; i--) {
			if (last != lengths[i]) {
				last = lengths[i];
				lastCount = 0;
			}
			pairs[i] = pairs[i + 1] + lastCount++;
		}
		int[] qty = new int[count];
		for (int i = 0; i < count; i++)
			qty[i] = (int)(long)counter.get(lengths[i]);
		for (int i = 0; i < count; i++) {
			int k = 0;
			for (int j = i + 1; j < count; j++) {
				while (lengths[i] + 2 * lengths[k] <= lengths[j])
					k++;
				answer += pairs[k];
				if (i >= k)
					answer -= qty[i] - 1;
				if (j >= k)
					answer -= qty[j] - 1;
				if (lengths[i] == lengths[j])
					answer++;
			}
		}
		last = -1;
		for (int i = 0; i < count; i++) {
			if (lengths[i] == last)
				continue;
			last = lengths[i];
			answer -= (long)qty[i] * (qty[i] - 1) * (qty[i] - 2) * (qty[i] - 3) / 24 * 5;
			int secondLast = last;
			for (int j = i + 1; j < count; j++) {
				if (lengths[j] == secondLast)
					continue;
				secondLast = lengths[j];
				answer -= (long)qty[i] * (qty[i] - 1) * qty[j] * (qty[j] - 1) / 4;
			}
			secondLast = -1;
			for (int j = 0; j < count; j++) {
				if (lengths[j] == secondLast)
					continue;
				secondLast = lengths[j];
				if (lengths[j] != lengths[i] && lengths[j] < 3 * lengths[i])
					answer -= (long)qty[i] * (qty[i] - 1) * (qty[i] - 2) * qty[j] / 3;
			}
		}
		out.printLine(answer);
	}
}
