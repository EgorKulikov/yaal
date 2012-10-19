package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskK {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int columnCount = in.readInt();
		int peakCount = in.readInt();
		int[][] gcd = new int[1001][columnCount + 1];
		for (int i = 0; i <= 1000; i++) {
			gcd[i][0] = i;
		}
		for (int i = 0; i <= columnCount; i++) {
			gcd[0][i] = i;
		}
		for (int i = 1; i <= 1000; i++) {
			for (int j = 0; j <= columnCount; j++) {
				if (i >= j) {
					gcd[i][j] = gcd[i - j][j];
				} else {
					gcd[i][j] = gcd[i][j - i];
				}
			}
		}
		int[] x = new int[peakCount];
		int[] y = new int[peakCount];
		IOUtils.readIntArrays(in, x, y);
		int[][] answer = new int[1001][columnCount + 1];
		for (int i = 0; i <= 1000; i++)
			answer[i][0] = 1;
		int last = -1;
		for (int i = 0; i < peakCount; i++) {
			int next;
			if (i == peakCount - 1)
				next = columnCount + 1;
			else
				next = x[i + 1];
			for (int j = 0; j < y[i]; j++) {
				for (int k = last + 1; k < x[i]; k++) {
					int dy = y[i] - j;
					int dx = x[i] - k;
					if (gcd[dy][dx] != 1)
						continue;
					int sum = 0;
					int xx = k;
					int yy = j;
					while (xx > last && yy >= 0) {
						sum += answer[yy][xx];
						xx -= dx;
						yy -= dy;
					}
					xx = x[i] + dx;
					yy = j;
					while (xx < next && yy >= 0) {
						answer[yy][xx] += sum;
						xx += dx;
						yy -= dy;
					}
				}
			}
			last = x[i];
		}
		long total = 0;
		for (int i = 0; i <= 1000; i++)
			total += answer[i][columnCount];
		total = total & ((1 << 30) - 1);
		out.printLine(total);
	}
}
