package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

public class Task7 {
	static int[] result = new int[100001];

	static {
		int[] sum = new int[100001];
		for (int i = 2; i <= 100000; i++) {
			if (i == Integer.highestOneBit(i)) {
				sum[i] = sum[i - 1];
				continue;
			}
			Set<Integer> possible = new HashSet<Integer>();
			for (int j = 2; j * j <= 2 * i; j++) {
				if (2 * i % j != 0)
					continue;
				int k = 2 * i / j;
				if (j % 2 == 0 && k % 2 == 0)
					continue;
				int max = (k + j - 1) / 2;
				int min = (k - j + 1) / 2;
				possible.add(sum[max] ^ sum[min - 1]);
			}
			for (int j = 0; ; j++) {
				if (!possible.contains(j)) {
					result[i] = j;
					break;
				}
			}
			sum[i] = sum[i - 1] ^ result[i];
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = 0;
		try {
		count = in.readInt();
		} catch (InputMismatchException e) {
			out.printLine("RON");
			return;}
		int answer = 0;
		int value = count;
		for (int i = 0; i < count; i++) {
			try {
				value = in.readInt();
			} catch (InputMismatchException ignored) {}
			answer ^= result[value];
		}
		if (answer == 0)
			out.printLine("RON");
		else
			out.printLine("HARRY");
	}
}
