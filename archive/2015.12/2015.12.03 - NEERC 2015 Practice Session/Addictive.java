package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Addictive {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int h = in.readInt();
		int w = in.readInt();
		int c = in.readInt();
		int[] qty = IOUtils.readIntArray(in, c);
		char[][] answer = new char[h][w];
		int at = 0;
		for (int i = 0; i < h; i++) {
			if ((i & 1) == 0) {
				for (int j = 0; j < w; j++) {
					if (qty[at] == 0)
						at++;
					answer[i][j] = (char) ('1' + at);
					qty[at]--;
				}
			} else {
				for (int j = w - 1; j >= 0; j--) {
					if (qty[at] == 0)
						at++;
					answer[i][j] = (char) ('1' + at);
					qty[at]--;
				}
			}
		}
		Arrays.asList(answer).forEach(out::printLine);
	}
}
