package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] f = in.readString().toCharArray();
		int m = in.readInt();
		long[] current = new long[1 << f.length];
		current[0] = 1;
		for (int i = 0; i < f.length; i++) {
			if (f[i] == 'D') {
				f[i] = 0;
			} else if (f[i] == 'R') {
				f[i] = 1;
			} else if (f[i] == 'I') {
				f[i] = 2;
			} else {
				f[i] = 3;
			}
		}
		int[][] nextJ = new int[current.length][4];
		for (int j = 0; j < current.length; j++) {
			for (int l = 0; l < 4; l++) {
				int nj = j;
				int kill = 0;
				for (int k = 0; k < f.length; k++) {
					if (kill == 0 && (nj >> k & 1) == 0 && f[k] == l) {
						nj += 1 << k;
						kill = 1;
					} else if (kill == 1 && (nj >> k & 1) == 1) {
						nj -= 1 << k;
						kill = 0;
					}
				}
				nextJ[j][l] = nj;
			}
		}
		long[] next = new long[current.length];
		for (int i = 0; i < m; i++) {
			Arrays.fill(next, 0);
			for (int j = 0; j < current.length; j++) {
				current[j] %= MOD;
				for (int l = 0; l < 4; l++) {
//					int nj = j;
//					int kill = 0;
//					for (int k = 0; k < f.length; k++) {
//						if (kill == 0 && (nj >> k & 1) == 0 && f[k] == l) {
//							nj += 1 << k;
//							kill = 1;
//						} else if (kill == 1 && (nj >> k & 1) == 1) {
//							nj -= 1 << k;
//							kill = 0;
//						}
//					}
					next[nextJ[j][l]] += current[j];
				}
			}
			long[] temp = current;
			current = next;
			next = temp;
		}
		long[] answer = new long[f.length + 1];
		for (int i = 0; i < current.length; i++) {
			answer[Integer.bitCount(i)] += current[i];
		}
		for (int i = 0; i <= f.length; i++) {
			answer[i] %= MOD;
		}
		for (long l : answer) {
			out.printLine(l);
		}
	}
}
