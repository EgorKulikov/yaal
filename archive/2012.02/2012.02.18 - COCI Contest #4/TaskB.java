package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] temperature = IOUtils.readIntArray(in, count);
		int winterLength = 0;
		int winterComing = 0;
		boolean[] canAnnounce = new boolean[count];
		int longestWinter = 0;
		List<Integer> longestWinterIndices = new ArrayList<Integer>();
		int answer = 0;
		for (int i = count - 1; i >= -1; i--) {
			if (i == -1 || temperature[i] >= 0) {
				if (winterLength > longestWinter) {
					longestWinter = winterLength;
					longestWinterIndices = new ArrayList<Integer>();
					longestWinterIndices.add(i + 1);
				} else if (winterLength == longestWinter)
					longestWinterIndices.add(i + 1);
				winterComing = Math.max(winterComing, 2 * winterLength);
				winterLength = 0;
			} else
				winterLength++;
			if (i != -1 && winterComing != 0) {
				winterComing--;
				canAnnounce[i] = true;
				answer++;
			}
		}
		int[] sum = new int[count + 1];
		for (int i = 0; i < count; i++)
			sum[i + 1] = sum[i] + (canAnnounce[i] ? 1 : 0);
		int maxDelta = 0;
		for (int i : longestWinterIndices) {
			int start = Math.max(0, i - 3 * longestWinter);
			int finish = Math.max(0, i - 2 * longestWinter);
			maxDelta = Math.max(maxDelta, finish - start + sum[start] - sum[finish]);
		}
		answer += maxDelta;
		out.printLine(answer);
	}
}
