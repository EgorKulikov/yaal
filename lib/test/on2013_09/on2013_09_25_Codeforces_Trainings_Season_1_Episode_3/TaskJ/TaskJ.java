package on2013_09.on2013_09_25_Codeforces_Trainings_Season_1_Episode_3.TaskJ;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		char[][] mines = IOUtils.readTable(in, size, size);
		char[][] moves = IOUtils.readTable(in, size, size);
		boolean showMines = false;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (moves[i][j] == 'x') {
					if (mines[i][j] == '*') {
						showMines = true;
						continue;
					}
					char current = '0';
					for (int k = 0; k < 8; k++) {
						int row = i + MiscUtils.DX8[k];
						int column = j + MiscUtils.DY8[k];
						if (MiscUtils.isValidCell(row, column, size, size) && mines[row][column] == '*')
							current++;
					}
					moves[i][j] = current;
				}
			}
		}
		if (showMines) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (mines[i][j] == '*')
						moves[i][j] = '*';
				}
			}
		}
		for (char[] row : moves)
			out.printLine(row);
    }
}
