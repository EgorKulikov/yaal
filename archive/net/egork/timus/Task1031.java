package net.egork.timus;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class Task1031 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int[] l = in.readIntArray(3);
		int[] c = in.readIntArray(3);
		int n = in.readInt();
		int start = in.readInt() - 1;
		int finish = in.readInt() - 1;
		if (start > finish) {
			int temp = start;
			start = finish;
			finish = temp;
		}
		int[] distances = new int[n];
		System.arraycopy(in.readIntArray(n - 1), 0, distances, 1, n - 1);
		long[] cost = new long[n];
		int[] pointer = new int[3];
		Arrays.fill(pointer, start);
		for (int i = start + 1; i <= finish; i++) {
			cost[i] = Long.MAX_VALUE;
			for (int j = 0; j < 3; j++) {
				while (distances[i] - distances[pointer[j]] > l[j])
					pointer[j]++;
				if (pointer[j] != i)
					cost[i] = Math.min(cost[i], cost[pointer[j]] + c[j]);
			}
		}
		out.println(cost[finish]);
	}
}

