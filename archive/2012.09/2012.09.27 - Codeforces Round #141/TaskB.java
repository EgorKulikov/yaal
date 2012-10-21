package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int firstRowCount = in.readInt();
		int firstColumnCount = in.readInt();
		char[][] firstTable = IOUtils.readTable(in, firstRowCount, firstColumnCount);
		int secondRowCount = in.readInt();
		int secondColumnCount = in.readInt();
		char[][] secondTable = IOUtils.readTable(in, secondRowCount, secondColumnCount);
		int answer = 0;
		int rowShift = -1;
		int columnShift = -1;
		for (int i = -firstRowCount; i < secondRowCount; i++) {
			for (int j = -firstColumnCount; j < secondColumnCount; j++) {
				int current = 0;
				for (int k = 0; k < firstRowCount; k++) {
					for (int l = 0; l < firstColumnCount; l++) {
						if (i + k >= 0 && i + k < secondRowCount && j + l >= 0 && j + l < secondColumnCount && firstTable[k][l] == '1' && secondTable[i + k][j + l] == '1')
							current++;
					}
				}
				if (current > answer) {
					answer = current;
					rowShift = i;
					columnShift = j;
				}
			}
		}
		out.printLine(rowShift, columnShift);
	}
}
