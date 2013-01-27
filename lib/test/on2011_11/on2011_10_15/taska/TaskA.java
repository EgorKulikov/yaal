package on2011_11.on2011_10_15.taska;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[][] table = IOUtils.readTable(in, 8, 8);
		table[0][7] = '.';
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 8; k++) {
					if (table[j][k] == 'M') {
						for (int l = 0; l < 8; l++) {
							int row = j + MiscUtils.DX8[l];
							int column = k + MiscUtils.DY8[l];
							if (row >= 0 && row < 8 && column >= 0 && column < 8 && table[row][column] == '.')
								table[row][column] = 'X';
						}
					}
				}
			}
			for (int j = 7; j >= 0; j--) {
				for (int k = 0; k < 8; k++) {
					if (table[j][k] == 'S') {
						if (j < 7)
							table[j + 1][k] = 'S';
						table[j][k] = '.';
					}
				}
			}
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 8; k++) {
					if (table[j][k] == 'X')
						table[j][k] = 'M';
				}
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (table[i][j] == 'M') {
					out.printLine("WIN");
					return;
				}
			}
		}
		out.printLine("LOSE");
	}
}
