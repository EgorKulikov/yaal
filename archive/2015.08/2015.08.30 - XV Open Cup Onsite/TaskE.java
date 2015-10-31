package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] c = IOUtils.readIntArray(in, n);
		c = Arrays.copyOf(c, n + 1);
		c[n] = c[0];
		for (int i = 0; i <= n; i++) {
			c[i] *= 2;
		}
		int[] queue = new int[n * 10];
		int[] select = new int[n];
		int size = 0;
		boolean[] inQueue = new boolean[n];
		int[] by = new int[n];
		for (int i = 0; i < n; i++) {
			select[i] = c[i] > c[i + 1] ? i : i + 1;
			if (select[i] == n) {
				select[i] = 0;
			}
			by[select[i]]++;
			queue[size++] = i;
			inQueue[i] = true;
		}
		for (int i = 0; i < size; i++) {
			int current = queue[i];
			inQueue[current] = false;
			int cVal = c[select[current]];
			if (by[select[current]] == 2) {
				cVal >>= 1;
			}
			int alternative = 2 * current + 1 - select[current];
			if (alternative == n) {
				alternative = 0;
			} else if (alternative == 2 * n - 1) {
				alternative = n - 1;
			}
			int altVal = c[alternative];
			if (by[alternative] == 1) {
				continue;
			}
			if (altVal > cVal) {
				by[select[current]]--;
				by[alternative]++;
				select[current] = alternative;
				int next = current + 1;
				if (next == n) {
					next = 0;
				}
				if (!inQueue[next]) {
					inQueue[next] = true;
					queue[size++] = next;
				}
				int last = current - 1;
				if (last == -1) {
					last = n - 1;
				}
				if (!inQueue[last]) {
					inQueue[last] = true;
					queue[size++] = last;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			select[i]++;
		}
		out.printLine(select);
	}
}
