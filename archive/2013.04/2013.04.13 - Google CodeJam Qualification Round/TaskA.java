package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[][] field = IOUtils.readTable(in, 4, 4);
		boolean xWon = false;
		boolean oWon = false;
		boolean draw = true;
		for (int i = 0; i < 4; i++) {
			boolean curX = true;
			boolean curO = true;
			for (int j = 0; j < 4; j++) {
				if (field[i][j] == '.') {
					curX = false;
					curO = false;
					draw = false;
				} else if (field[i][j] == 'X')
					curO = false;
				else if (field[i][j] == 'O')
					curX = false;
			}
			xWon |= curX;
			oWon |= curO;
		}
		for (int i = 0; i < 4; i++) {
			boolean curX = true;
			boolean curO = true;
			for (int j = 0; j < 4; j++) {
				if (field[j][i] == '.') {
					curX = false;
					curO = false;
					draw = false;
				} else if (field[j][i] == 'X')
					curO = false;
				else if (field[j][i] == 'O')
					curX = false;
			}
			xWon |= curX;
			oWon |= curO;
		}
		boolean curX = true;
		boolean curO = true;
		for (int j = 0; j < 4; j++) {
			if (field[j][j] == '.') {
				curX = false;
				curO = false;
				draw = false;
			} else if (field[j][j] == 'X')
				curO = false;
			else if (field[j][j] == 'O')
				curX = false;
		}
		xWon |= curX;
		oWon |= curO;
		curX = true;
		curO = true;
		for (int j = 0; j < 4; j++) {
			if (field[j][3 - j] == '.') {
				curX = false;
				curO = false;
				draw = false;
			} else if (field[j][3 - j] == 'X')
				curO = false;
			else if (field[j][3 - j] == 'O')
				curX = false;
		}
		xWon |= curX;
		oWon |= curO;
		if (xWon)
			out.printLine("Case #" + testNumber + ": X won");
		else if (oWon)
			out.printLine("Case #" + testNumber + ": O won");
		else if (draw)
			out.printLine("Case #" + testNumber + ": Draw");
		else
			out.printLine("Case #" + testNumber + ": Game has not completed");
    }
}
