package net.egork;

import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class ASCII {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int h = in.readInt();
		int w = in.readInt();
		char[][] map = IOUtils.readTable(in, h, w);
		int answer = 0;
		boolean inside = false;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j] == '.') {
					if (inside)
						answer += 2;
				} else {
					inside = !inside;
					answer++;
				}
			}
		}
		out.printLine(answer / 2);
	}
}
