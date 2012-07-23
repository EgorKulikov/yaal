package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		int mod = in.readInt();
		int seed = in.readInt();
		int[] index = new int[mod];
		Arrays.fill(index, -1);
		for (int i = 0; ; i++) {
			seed = (int)(((long)a * seed + b) % mod);
			if (index[seed] != -1) {
				out.printLine(i - index[seed]);
				return;
			}
			index[seed] = i;
		}
	}
}
