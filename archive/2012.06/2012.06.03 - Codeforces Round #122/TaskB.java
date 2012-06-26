package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int moveCount = in.readInt();
		int r = in.readInt();
		int[] a = IOUtils.readIntArray(in, count);
		int[] b = IOUtils.readIntArray(in, count);
		int[] k = IOUtils.readIntArray(in, count);
		int[] p = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(p);
		out.printLine(go(a, b, k, p, r, moveCount));
	}

	private long go(int[] a, int[] b, int[] k, int[] p, int r, int moveCount) {
		long answer = makeAdd(a, b, k, p, r, moveCount);
		if (moveCount != 0) {
			for (int i = 0; i < a.length; i++)
				a[i] ^= b[i];
			answer = Math.max(answer, makeAdd(a, b, k, p, r, moveCount - 1));
		}
		return answer;
	}

	private long makeAdd(int[] a, int[] b, int[] k, int[] p, int r, int moveCount) {
		long answer = Long.MIN_VALUE;
		if (moveCount % 2 == 0) {
			answer = 0;
			for (int i = 0; i < a.length; i++)
				answer += (long)a[i] * k[i];
		}
		if (moveCount == 0)
			return answer;
		int[] newA = new int[a.length];
		for (int i = 0; i < a.length; i++)
			newA[i] = a[p[i]] + r;
		return Math.max(answer, go(newA, b, k, p, r, moveCount - 1));
	}
}
