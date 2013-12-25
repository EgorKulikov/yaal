package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] sequence = IOUtils.readIntArray(in, count);
		for (int i = 0; i < count; i++)
			sequence[i] = Math.abs(sequence[i]);
		int[] unique = sequence.clone();
		Arrays.sort(unique);
		unique = ArrayUtils.unique(unique);
		ArrayUtils.reverse(unique);
		int answer = 0;
		for (int i : unique) {
			int qty = ArrayUtils.count(sequence, i);
			int[] result = new int[qty + 1];
			int sureLess = 0;
			int sureMore = 0;
			int same = 0;
			Arrays.fill(result, Integer.MAX_VALUE);
			result[0] = 0;
			for (int j = 0; j < count; j++) {
				if (sequence[j] < i)
					sureLess++;
			}
			for (int j = 0; j < count; j++) {
				if (sequence[j] < i) {
					sureLess--;
					sureMore++;
				} else if (sequence[j] == i) {
					for (int k = same; k >= 0; k--) {
						result[k + 1] = Math.min(result[k + 1], result[k] + sureLess);
						result[k] += sureMore + k;
					}
					same++;
				}
			}
			answer += ArrayUtils.minElement(result);
		}
		out.printLine(answer);
    }
}
