package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class GreenBiathlon {
	private long[] distances;
	private long[][][] answer;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		distances = IOUtils.readLongArray(in, count + 1);
		long start = distances[count];
		Arrays.sort(distances);
		answer = new long[count + 1][count + 1][2];
		ArrayUtils.fill(answer, -1);
		int startPosition = Arrays.binarySearch(distances, start);
		answer[startPosition][startPosition][0] = answer[startPosition][startPosition][1] = 0;
		long result = Math.min(go(0, count, 0), go(0, count, 1)) + count;
		out.printLine(result);
	}

	private long go(int from, int to, int side) {
		if (answer[from][to][side] != -1)
			return answer[from][to][side];
		if (from == to)
			return Long.MAX_VALUE / 2;
		int shots = answer.length - (to - from) + 1;
		if (side == 0)
			answer[from][to][side] = Math.min(go(from + 1, to, 0) + shots * (distances[from + 1] - distances[from]), go(from + 1, to, 1) + shots * (distances[to] - distances[from]));
		else
			answer[from][to][side] = Math.min(go(from, to - 1, 0) + shots * (distances[to] - distances[from]), go(from, to - 1, 1) + shots * (distances[to] - distances[to - 1]));
		return answer[from][to][side];
	}
}
