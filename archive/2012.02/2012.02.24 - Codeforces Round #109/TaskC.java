package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] multipliers = new int[]{43, 47, 41, 37, 31, 53, 59};
		int count = in.readInt();
		int edgeCount = in.readInt();
		long[][] powers = new long[multipliers.length][count];
		for (int i = 0; i < multipliers.length; i++) {
			powers[i][0] = 1;
			for (int j = 1; j < count; j++)
				powers[i][j] = powers[i][j - 1] * multipliers[i];
		}
		long[][] hashes = new long[multipliers.length][count];
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			for (int j = 0; j < multipliers.length; j++) {
				hashes[j][from] += powers[j][to];
				hashes[j][to] += powers[j][from];
			}
		}
		long[] resultHash = new long[count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < multipliers.length; j++)
				resultHash[i] += hashes[j][i];
		}
		Arrays.sort(resultHash);
		long current = resultHash[0];
		long start = 0;
		long answer = 0;
		for (int i = 1; i < count; i++) {
			if (resultHash[i] != current) {
				answer += (i - start) * (i - start - 1) / 2;
				start = i;
				current = resultHash[i];
			}
		}
		answer += (count - start) * (count - start - 1) / 2;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < multipliers.length; j++)
				hashes[j][i] += powers[j][i];
		}
		for (int i = 0; i < count; i++) {
			resultHash[i] = 0;
			for (int j = 0; j < multipliers.length; j++)
				resultHash[i] += hashes[j][i];
		}
		Arrays.sort(resultHash);
		current = resultHash[0];
		start = 0;
		for (int i = 1; i < count; i++) {
			if (resultHash[i] != current) {
				answer += (i - start) * (i - start - 1) / 2;
				start = i;
				current = resultHash[i];
			}
		}
		answer += (count - start) * (count - start - 1) / 2;
		out.printLine(answer);
	}
}
