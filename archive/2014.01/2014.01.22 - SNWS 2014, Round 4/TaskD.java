package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int queryCount = in.readInt();
		int count = in.readInt();
		int[] size = IOUtils.readIntArray(in, count);
		Arrays.sort(size);
		int[] answer = new int[size[count - 1]];
		Arrays.fill(answer, Integer.MAX_VALUE);
		answer[0] = 0;
		for (int i : size) {
			if (i == answer.length)
				continue;
			int gcd = IntegerUtils.gcd(i, answer.length);
			int tries = answer.length / gcd * 2;
			for (int j = 0; j < gcd; j++) {
				int current = j;
				for (int k = 0; k < tries; k++) {
					int next = current + i;
					if (next >= answer.length)
						next -= answer.length;
					if (answer[current] != Integer.MAX_VALUE) {
						if (next > current)
							answer[next] = Math.min(answer[next], answer[current] + 1);
						else
							answer[next] = Math.min(answer[next], answer[current]);
					}
					current = next;
				}
			}
		}
		for (int i = 0; i < queryCount; i++) {
			long query = in.readLong();
			int delta = answer[((int) (query % answer.length))];
			if (delta == Integer.MAX_VALUE)
				out.printLine(-1);
			else
				out.printLine(query / answer.length + delta);
		}
    }
}
