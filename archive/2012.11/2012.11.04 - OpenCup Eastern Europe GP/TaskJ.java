package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] job = new int[count];
		int[] until = new int[count];
		IOUtils.readIntArrays(in, job, until);
		int[] order = ArrayUtils.order(until);
		int answer = Integer.MAX_VALUE;
		int sum = 0;
		for (int i : order) {
			sum += job[i];
			answer = Math.min(answer, until[i] - sum);
		}
		out.printLine(answer);
	}
}
