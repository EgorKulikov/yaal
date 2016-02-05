package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Random;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = IOUtils.readIntArray(in, n);
		int[] b = IOUtils.readIntArray(in, n);
		int[] aOrder = ArrayUtils.order(a);
		int[] bOrder = ArrayUtils.order(b);
		ArrayUtils.reverse(aOrder);
		ArrayUtils.reverse(bOrder);
		int[] fA = new int[n];
		int[] fB = new int[n];
		int[] was = new int[n + 1];
		for (int i = 0; i < n; i++) {
			was[a[i]] = i + 1;
		}
		for (int i = 0; i < n; i++) {
			if (was[b[i]] != 0) {
				out.printLine(1);
				out.printLine(was[b[i]]);
				out.printLine(1);
				out.printLine(i + 1);
				return;
			}
		}
		Random r = new Random(239);
		for (int j = 0; ; j++) {
			int aSize = 0;
			int bSize = 0;
			int atA = 0;
			int atB = 0;
			int delta = 0;
			int cc = j;
			while (true) {
				if (delta >= 0) {
					if (atA == n) {
						break;
					}
					if (a[aOrder[atA]] == delta) {
						fA[aSize++] = aOrder[atA] + 1;
						print(out, fA, aSize, fB, bSize);
						return;
					}
					if ((cc & 1) != 1) {
						fA[aSize++] = aOrder[atA] + 1;
						delta -= a[aOrder[atA]];
					}
					cc >>= 1;
					atA++;
				} else {
					if (atB == n) {
						break;
					}
					if (b[bOrder[atB]] == -delta) {
						fB[bSize++] = bOrder[atB] + 1;
						print(out, fA, aSize, fB, bSize);
						return;
					}
					if ((cc & 1) != 1) {
						fB[bSize++] = bOrder[atB] + 1;
						delta += b[bOrder[atB]];
					}
					cc >>= 1;
					atB++;
				}
			}
		}
//		out.printLine(-1);
	}

	private void print(OutputWriter out, int[] fA, int aSize, int[] fB, int bSize) {
		Arrays.sort(fA, 0, aSize);
		Arrays.sort(fB, 0, bSize);
		out.printLine(aSize);
		out.printLine(Arrays.copyOf(fA, aSize));
		out.printLine(bSize);
		out.printLine(Arrays.copyOf(fB, bSize));
	}
}
