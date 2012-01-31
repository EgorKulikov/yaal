package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Mouse {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int count = in.readInt();
		int[] xl = new int[count];
		int[] yt = new int[count];
		int[] xr = new int[count];
		int[] yb = new int[count];
		IOUtils.readIntArrays(in, xl, yt, xr, yb);
		int queryCount = in.readInt();
		xl = Arrays.copyOf(xl, count + queryCount);
		yt = Arrays.copyOf(yt, count + queryCount);
		xr = Arrays.copyOf(xr, count + queryCount);
		yb = Arrays.copyOf(yb, count + queryCount);
		int[] index = new int[count + queryCount];
		for (int i = 0; i < count; i++)
			index[i] = i + 1;
		int total = count;
		for (int i = 0; i < queryCount; i++) {
			int x = in.readInt();
			int y = in.readInt();
			int answer = 0;
			for (int j = total - 1; j >= 0; j--) {
				if (x >= xl[j] && x <= xr[j] && y >= yb[j] && y <= yt[j]) {
					answer = index[j];
					break;
				}
			}
			out.printLine(answer);
			if (answer != 0) {
				xl[total] = xl[answer - 1];
				xr[total] = xr[answer - 1];
				yb[total] = yb[answer - 1];
				yt[total] = yt[answer - 1];
				index[total++] = answer;
			}
		}
	}
}
