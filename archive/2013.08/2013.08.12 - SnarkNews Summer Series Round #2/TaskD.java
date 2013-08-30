package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int minSide = in.readInt();
		String bad = in.readString();
		boolean[] isBad = new boolean[26];
		for (int i = 0; i < bad.length(); i++)
			isBad[bad.charAt(i) - 'A'] = true;
		char[][] frame = IOUtils.readTable(in, rowCount, columnCount);
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				frame[i][j] -= 'A';
			}
		}
		int[][] qty = new int[rowCount + 1][columnCount + 1];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				qty[i + 1][j + 1] = qty[i + 1][j] + qty[i][j + 1] - qty[i][j] + (isBad[frame[i][j]] ? 1 : 0);
			}
		}
		int answerBad = 0;
		int answerTotal = 1;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				for (int k = i + minSide; k <= rowCount; k++) {
					for (int l = j + minSide; l <= columnCount; l++) {
						int currentBad = qty[k][l] + qty[i][j] - qty[k][j] - qty[i][l];
						int currentTotal = (k - i) * (l - j);
						if (currentBad * answerTotal > answerBad * currentTotal || currentBad * answerTotal == answerBad * currentTotal && answerTotal < currentTotal) {
							answerBad = currentBad;
							answerTotal = currentTotal;
						}
					}
				}
			}
		}
		out.printLine(answerBad + "/" + answerTotal);
    }
}
