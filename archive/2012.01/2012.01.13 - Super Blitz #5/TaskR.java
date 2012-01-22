package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskR {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] index = new int[30001];
		Arrays.fill(index, -1);
		for (int i = 0; i < count; i++) {
			int money = in.readInt();
			if (index[money] == -1)
				index[money] = i + 1;
			else
				index[money] = -2;
		}
		for (int i = 1; i <= 30000; i++) {
			if (index[i] > 0) {
				out.printLine(index[i]);
				return;
			}
		}
		out.printLine(0);
	}
}
