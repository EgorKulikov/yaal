package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Symmetry {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		for (int i = 0; i < count; i++) {
			x[i] *= 2;
			y[i] *= 2;
		}
		int[] hashes = new int[count];
		for (int i = 0; i < count; i++)
			hashes[i] = 40001 * x[i] + y[i];
		Arrays.sort(hashes);
		int answer = 0;
		for (int i = 1; i < count; i++) {
			int a = x[i] - x[0];
			int b = y[i] - y[0];
			int midX = (x[i] + x[0]) / 2;
			int midY = (y[i] + y[0]) / 2;
			int c = -a * midX - b * midY;
			boolean good = goodLine(count, x, y, hashes, a, b, c);
			if (good)
				answer++;
		}
		for (int i = 2; i < count; i++) {
			int a = x[i] - x[1];
			int b = y[i] - y[1];
			int midX = (x[i] + x[1]) / 2;
			int midY = (y[i] + y[1]) / 2;
			int c = -a * midX - b * midY;
			if (a * x[0] + b * y[0] + c != 0)
				continue;
			boolean good = goodLine(count, x, y, hashes, a, b, c);
			if (good)
				answer++;
		}
		int a = y[0] - y[1];
		int b = x[1] - x[0];
		int c = -a * x[0] - b * y[0];
		if (goodLine(count, x, y, hashes, a, b, c))
			answer++;
		out.printLine(answer);
	}

	private boolean goodLine(int count, int[] x, int[] y, int[] hashes, int a, int b, int c) {
		boolean good = true;
		for (int j = 0; j < count && good; j++) {
			int a0 = -b;
			int b0 = a;
			int c0 = -x[j] * a0 - y[j] * b0;
			int d = a0 * b - a * b0;
			int curX = c * b0 - b * c0;
			int curY = a * c0 - c * a0;
			if (curX % d != 0 || curY % d != 0)
				good = false;
			else {
				curX /= d;
				curY /= d;
				curX = 2 * curX - x[j];
				curY = 2 * curY - y[j];
				if (Arrays.binarySearch(hashes, curX * 40001 + curY) < 0)
					good = false;
			}
		}
		return good;
	}
}
