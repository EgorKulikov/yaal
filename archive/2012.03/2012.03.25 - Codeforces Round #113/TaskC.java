package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int x = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		Arrays.sort(array);
		int answer = 0;
		while (array[(array.length - 1) / 2] != x) {
			array = Arrays.copyOf(array, array.length + 1);
			array[array.length - 1] = x;
			Arrays.sort(array);
			answer++;
		}
		out.printLine(answer);
	}
}
