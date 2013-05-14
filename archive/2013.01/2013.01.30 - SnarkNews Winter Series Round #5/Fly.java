package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Fly {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		int shotCount = in.readInt();
		int[] xx = new int[shotCount];
		int[] yy = new int[shotCount];
		IOUtils.readIntArrays(in, xx, yy);
		int answer = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < shotCount; j++) {
				if (Math.abs(x[i] - xx[j]) <= 50 && Math.abs(y[i] - yy[j]) <= 50) {
					answer++;
					break;
				}
			}
		}
		out.printLine(answer);
	}
}
