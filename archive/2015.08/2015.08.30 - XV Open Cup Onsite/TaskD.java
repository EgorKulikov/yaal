package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	static final int BUBEN = (int) 500;
	static final long INF = Long.MAX_VALUE / 2;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long[] k = new long[BUBEN * BUBEN + 1];
		long[] s = new long[BUBEN + 1];
		Arrays.fill(k, INF);
		k[0] = 0;
		s[0] = 0;
		for (int i = 1; i <= BUBEN; ++i) {
			s[i] = i * i + s[i - 1];
			for (int j = BUBEN * BUBEN; j >= i * i; --j) if (k[j] == INF && k[j - i * i] < INF) k[j] = i;
		}
		long n = in.readLong();
		if (n <= BUBEN * BUBEN) {
			long res = k[(int) n];
			out.print(res == INF ? "-" : "" + res);
			out.print(" ");
			int og = 0;
			for (int i = 1; i <= n; ++i) {
				if (k[i] - 1 > BUBEN || i <= s[(int) (k[i] - 1)]) {
					++og;
				}
			}
			out.printLine(og);
		} else {
			long skipEach = 0;
			for (int i = 1; i < k.length; ++i) if (k[i] == INF) ++skipEach;
			int initial = 0;
			while (s[initial + 1] <= BUBEN * BUBEN) ++initial;
			long og = 0;
			for (int i = 1; i <= s[initial]; ++i) {
				if (k[i] - 1 > BUBEN || i <= s[(int) (k[i] - 1)]) {
					++og;
				}
			}
			long cs = s[initial];
			while (cs + (initial + 1) * (long) (initial + 1) < n) {
				cs += (initial + 1) * (long) (initial + 1);
				og += skipEach;
				++initial;
			}
			++initial;
			long delta = cs + initial * (long) initial - n;
			if (delta < 0) throw new RuntimeException();
			if (delta >= k.length || k[(int) delta] < INF) {
				out.print(initial);
			} else {
				out.print(initial + 1);
			}
			out.print(" ");
			for (int i = 1; i < k.length; ++i) if (k[i] == INF && i >= delta) ++og;
			out.printLine(og);
		}
	}
}
