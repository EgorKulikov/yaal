import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] garden = IOUtils.readTable(in, rowCount, columnCount);
		int currentPosition = 0;
		int currentDirection = 1;
		int lastRow = rowCount - 1;
		while (lastRow > 0) {
			boolean hasWeed = false;
			for (int i = 0; i < columnCount; i++) {
				if (garden[lastRow][i] == 'W') {
					hasWeed = true;
					break;
				}
			}
			if (hasWeed)
				break;
			lastRow--;
		}
		int answer = lastRow;
		for (int i = 0; i <= lastRow; i++) {
			int nextPosition = currentPosition;
			if (currentDirection == 1) {
				for (int j = columnCount - 1; j > currentPosition; j--) {
					if (garden[i][j] == 'W' || i != rowCount - 1 && garden[i + 1][j] == 'W') {
						nextPosition = j;
						break;
					}
				}
			} else {
				for (int j = 0; j < currentPosition; j++) {
					if (garden[i][j] == 'W' || i != rowCount - 1 && garden[i + 1][j] == 'W') {
						nextPosition = j;
						break;
					}
				}
			}
			answer += Math.abs(nextPosition - currentPosition);
			currentPosition = nextPosition;
			currentDirection = -currentDirection;
		}
		out.println(answer);
	}
}

