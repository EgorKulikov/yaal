package on2011_10_28.task1712;



import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1712 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[][] table = IOUtils.readTable(in, 4, 4);
		char[][] password = IOUtils.readTable(in, 4, 4);
		StringBuilder answer = new StringBuilder(16);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					if (table[j][k] == 'X')
						answer.append(password[j][k]);
				}
			}
			char[][] nextTable = new char[4][4];
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++)
					nextTable[k][3 - j] = table[j][k];
			}
			table = nextTable;
		}
		out.printLine(answer);
	}
}
