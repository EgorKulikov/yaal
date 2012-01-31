package on2011_10_28.task1878;



import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1878 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[][] table = IOUtils.readIntTable(in, 4, 4);
		int answer = 8;
		for (int i = 1; i <= 4; i++) {
			int current = 4;
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					if (table[j][k] == i)
						current--;
					if (table[j + 2][k + 2] == i)
						current++;
				}
			}
			answer = Math.min(answer, current);
		}
		out.printLine(answer);
	}
}
