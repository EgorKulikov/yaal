package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TennisTournament {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int goodCount = in.readInt();
		double pWin = in.readInt() / 100d;
		int[] indices = IOUtils.readIntArray(in, goodCount);
		Arrays.sort(indices);
		MiscUtils.decreaseByOne(indices);
		double[] answer = new double[goodCount];
		Arrays.fill(answer, 1);
		while (count != 1) {
			int nextCount = 0;
			for (int i = 0; i < goodCount; i++) {
				if ((indices[i] & 1) == 1 || i == goodCount - 1 || indices[i + 1] != indices[i] + 1) {
					indices[nextCount] = indices[i] >> 1;
					answer[nextCount++] = answer[i] * pWin;
				} else {
					indices[nextCount] = indices[i] >> 1;
					answer[nextCount++] = answer[i] * answer[i + 1] + answer[i] * (1 - answer[i + 1]) * pWin + answer[i + 1] * (1 - answer[i]) * pWin;
					i++;
				}
			}
			goodCount = nextCount;
			count >>= 1;
		}
		out.printLine(answer[0] * 100);
	}
}
