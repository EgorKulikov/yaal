import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskH implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int size = in.readInt();
		int[][] mesh = IOUtils.readIntTable(in, size, size);
		boolean[][] result = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (mesh[i][j] == 0) {
					int influence = 0;
					int neighborCount = 0;
					for (int k = 0; k < 4; k++) {
						int row = i + MiscUtils.DX4[k];
						int column = j + MiscUtils.DY4[k];
						if (row < 0 || row >= size || column < 0 || column >= size)
							continue;
						influence += mesh[row][column];
						neighborCount++;
					}
					if (neighborCount == 0)
						neighborCount = 1;
					neighborCount *= neighborCount;
					influence *= influence;
					for (int k = 0; k < size; k++) {
						for (int l = 0; l < size; l++) {
							int dRow = k - i;
							int dColumn = l - j;
							if ((dRow * dRow + dColumn * dColumn) * neighborCount <= influence)
								result[k][l] = true;
						}
					}
				}
			}
		}
		int answer = 0;
		for (boolean[] row : result)
			answer += CollectionUtils.count(Array.wrap(row), false);
		out.println(answer);

	}
}

