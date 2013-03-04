package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int[] position = new int[100001];
		Arrays.fill(position, -1);
		int[] answer = new int[count];
		for (int i = 0; i < count; i++) {
			if (i != 0)
				answer[i] = answer[i - 1];
			if (position[array[i]] != -1)
				answer[i] = Math.max(answer[i], answer[position[array[i]]] + 1);
			position[array[i]] = i;
		}
		out.printLine(answer[count - 1]);
	}
}
