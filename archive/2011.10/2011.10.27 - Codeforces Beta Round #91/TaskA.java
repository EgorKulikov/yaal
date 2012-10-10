package net.egork;

import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int from = in.readInt();
		int to = in.readInt();
		long[] happy = new long[(1 << 11) - 1];
		happy[1] = 4;
		happy[2] = 7;
		int first = 1;
		int last = 3;
		for (int i = 2; i <= 10; i++) {
			for (int j = 0; j < last - first; j++) {
				happy[last + 2 * j] = 10 * happy[first + j] + 4;
				happy[last + 2 * j + 1] = 10 * happy[first + j] + 7;
			}
			int next = last + 2 * (last - first);
			first = last;
			last = next;
		}
		long answer = 0;
		for (int i = 1; i < happy.length; i++)
			answer += Math.max(0, Math.min(happy[i], to) - Math.max(from - 1, happy[i - 1])) * happy[i];
		out.println(answer);
	}
}
