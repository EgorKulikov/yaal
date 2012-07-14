package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	private int[] cost;
	private char[] s;
	private int[][] mustTake;
	private int[][] allPalin;
	private int[][][] palin;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		cost = IOUtils.readIntArray(in, length);
		s = IOUtils.readCharArray(in, length);
		mustTake = new int[length][length];
		allPalin = new int[length][length];
		palin = new int[length][length][length];
		ArrayUtils.fill(mustTake, -2);
		ArrayUtils.fill(allPalin, -2);
		ArrayUtils.fill(palin, -2);
		int[] answer = new int[length + 1];
		for (int i = 0; i < length; i++) {
			answer[i + 1] = answer[i];
			for (int j = 0; j <= i; j++) {
				int value = getMustTake(j, i);
				if (value != -1)
					answer[i + 1] = Math.max(answer[i + 1], answer[j] + value);
			}
		}
		out.printLine(answer[length]);
	}

	private int getMustTake(int from, int to) {
		if (from > to)
			return 0;
		if (mustTake[from][to] != -2)
			return mustTake[from][to];
		mustTake[from][to] = -1;
		for (int i = from; i <= to; i++) {
			if (s[from] == s[i]) {
				int palinValue = getAllPalin(from, i);
				int remainingValue = getMustTake(i + 1, to);
				if (palinValue != -1 && remainingValue != -1)
					mustTake[from][to] = Math.max(mustTake[from][to], palinValue + remainingValue);
			}
		}
		return mustTake[from][to];
	}

	private int getAllPalin(int from, int to) {
		if (allPalin[from][to] != -2)
			return allPalin[from][to];
		allPalin[from][to] = -1;
		for (int i = 0; i <= to - from; i++) {
			if (cost[i] == -1)
				continue;
			int value = getPalin(from, to, i);
			if (value != -1)
				allPalin[from][to] = Math.max(allPalin[from][to], value + cost[i]);
		}
		return allPalin[from][to];
	}

	private int getPalin(int from, int to, int length) {
		if (length == -2)
			return -1;
		if (from == to && length == 0 || from > to && length == -1)
			return 0;
		if (length == -1)
			return getMustTake(from, to);
		if (length > to - from)
			return -1;
		if (palin[from][to][length] != -2)
			return palin[from][to][length];
		palin[from][to][length] = -1;
		if (s[from] == s[to])
			palin[from][to][length] = Math.max(palin[from][to][length], getPalin(from + 1, to - 1, length - 2));
		for (int i = from; i < to; i++) {
			if (s[i] == s[from]) {
				int segmentValue = getAllPalin(from, i);
				int remainingValue = getPalin(i + 1, to, length);
				if (segmentValue != -1 && remainingValue != -1)
					palin[from][to][length] = Math.max(palin[from][to][length], segmentValue + remainingValue);
			}
		}
		for (int i = to; i > from; i--) {
			if (s[i] == s[to]) {
				int segmentValue = getAllPalin(i, to);
				int remainingValue = getPalin(from, i - 1, length);
				if (segmentValue != -1 && remainingValue != -1)
					palin[from][to][length] = Math.max(palin[from][to][length], segmentValue + remainingValue);
			}
		}
		return palin[from][to][length];
	}
}
