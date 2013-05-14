import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class GardenSquares implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] garden = IOUtils.readTable(in, rowCount, columnCount);
		int result = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				for (int k = 1; i + k < rowCount && j + k < columnCount; k++) {
					if (garden[i][j] == garden[i + k][j] && garden[i][j] == garden[i][j + k] && garden[i][j] == garden[i + k][j + k])
						result++;
				}
			}
		}
		out.println(result);
	}
}

