package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int firstCount = in.readInt();
		int secondCount = in.readInt();
		int queryCount = in.readInt();
		int[] first = IOUtils.readIntArray(in, firstCount);
		int[] second = IOUtils.readIntArray(in, secondCount);
		int[] queries = IOUtils.readIntArray(in, queryCount);
		int[] firstQty = new int[1 << 16];
		for (int i : first)
			firstQty[i]++;
		int[] secondQty = new int[1 << 16];
		for (int i : second)
			secondQty[i]++;
		firstQty = pushDown(firstQty);
		secondQty = pushDown(secondQty);
		long[] answer = new long[1 << 16];
		for (int i = 0; i < answer.length; i++)
			answer[i] = (long)firstQty[i] * secondQty[i];
		for (int i = answer.length - 1; i >= 0; i--) {
			for (int j = i & (i - 1); j != 0; j = (j - 1) & i)
				answer[j] -= answer[i];
			if (i != 0)
				answer[0] -= answer[i];
		}
		for (int i : queries)
			out.printLine(answer[i]);
    }

	private int[] pushDown(int[] qty) {
		int[] result = new int[qty.length];
		for (int i = qty.length - 1; i >= 0; i--) {
			for (int j = i; j != 0; j = (j - 1) & i)
				result[j] += qty[i];
			result[0] += qty[i];
		}
		return result;
	}
}
