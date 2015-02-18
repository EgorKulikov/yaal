package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] puzzle = IOUtils.readTable(in, rowCount, columnCount);
		String[] words = IOUtils.readStringArray(in, count);
		boolean[][] checked = new boolean[rowCount][columnCount];
		boolean ambiguous = false;
		for (int i = 0; i < count; i++) {
			int found = 0;
			for (int j = 0; j < rowCount; j++) {
				for (int k = 0; k < columnCount; k++) {
					for (int l = 0; l < 8; l++) {
						int row = j;
						int column = k;
						boolean ok = true;
						for (int m = 0; m < words[i].length(); m++) {
							if (!MiscUtils.isValidCell(row, column, rowCount, columnCount) ||
								puzzle[row][column] != words[i].charAt(m))
							{
								ok = false;
								break;
							}
							row += MiscUtils.DX8[l];
							column += MiscUtils.DY8[l];
						}
						if (ok) {
							row = j;
							column = k;
							found++;
							for (int m = 0; m < words[i].length(); m++) {
								checked[row][column] = true;
								row += MiscUtils.DX8[l];
								column += MiscUtils.DY8[l];
							}
						}
					}
				}
			}
			if (found == 0) {
				out.printLine("no solution");
				return;
			} else if (found > 1) {
				if (words[i].length() == 1) {
					if (found > 8) {
						ambiguous = true;
					}
				} else if (!words[i].equals(StringUtils.reverse(words[i])) || found > 2) {
					ambiguous = true;
				}
			}
		}
		if (ambiguous) {
			out.printLine("ambiguous");
			return;
		}
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (!checked[i][j]) {
					answer.append(puzzle[i][j]);
				}
			}
		}
		if (answer.length() == 0) {
			out.printLine("empty spell");
		} else {
			out.printLine(answer);
		}
	}
}
