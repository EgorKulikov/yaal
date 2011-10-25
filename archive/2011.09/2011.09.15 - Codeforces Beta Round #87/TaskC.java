import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskC implements Solver {
	private static final int MOD = 1000003;

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] table = IOUtils.readTable(in, rowCount, columnCount);
		int answer = 1;
		for (int i = 0; i < rowCount; i++) {
			int type = 0;
			for (int j = 0; j < columnCount; j++) {
				if (table[i][j] != '.') {
					if (table[i][j] <= '2' && (j & 1) == 0 || table[i][j] >= '3' && (j & 1) == 1)
						type |= 1;
					else
						type |= 2;
				}
			}
			if (type == 3) {
				out.println(0);
				return;
			}
			if (type == 0) {
				answer *= 2;
				if (answer >= MOD)
					answer -= MOD;
			}
		}
		for (int i = 0; i < columnCount; i++) {
			int type = 0;
			for (int j = 0; j < rowCount; j++) {
				if (table[j][i] != '.') {
					if ((table[j][i] == '1' || table[j][i] == '4') && (j & 1) == 0 || (table[j][i] == '2' || table[j][i] == '3') && (j & 1) == 1)
						type |= 1;
					else
						type |= 2;
				}
			}
			if (type == 3) {
				out.println(0);
				return;
			}
			if (type == 0) {
				answer *= 2;
				if (answer >= MOD)
					answer -= MOD;
			}
		}
		out.println(answer);
	}
}

