package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CAOStage1 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		int answer = 0;
		for (int i = 2; i < rowCount - 2; i++) {
			for (int j = 2; j < columnCount - 2; j++) {
				boolean good = true;
				for (int k = 0; k < 4 && good; k++) {
					for (int l = 0; l <= 2 && good; l++) {
						if (map[i + MiscUtils.DX4[k] * l][j + MiscUtils.DY4[k] * l] == '#')
							good = false;
					}
				}
				if (good)
					answer++;
			}
		}
		out.printLine(answer);
    }
}
