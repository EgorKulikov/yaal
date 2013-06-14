package on2011_10.on2011_9_29.taskc;



import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		char[] balls = in.readString().toCharArray();
		int answer = Integer.MAX_VALUE;
		for (int i = 2; i < balls.length; i++) {
			if (balls.length % i != 0)
				continue;
			char[][] grid = new char[i][balls.length / i];
			int direction = 3;
			int row = 0;
			int column = 0;
			for (char ball : balls) {
				grid[row][column] = ball;
				for (int j = 0; j < 4; j++) {
					int newRow = row + MiscUtils.DX4[direction];
					int newColumn = column + MiscUtils.DY4[direction];
					if (newRow >= 0 && newRow < i && newColumn >= 0 && newColumn < balls.length / i &&
						grid[newRow][newColumn] == 0)
					{
						row = newRow;
						column = newColumn;
						break;
					}
					direction = (direction + 1) & 3;
				}
			}
			boolean good = true;
			for (char[] gridRow : grid) {
				for (int j = 0; j < gridRow.length && good; j++) {
					if (gridRow[j] != grid[0][j])
						good = false;
				}
				if (!good)
					break;
			}
			if (good)
				answer = Math.min(answer, i + balls.length / i);
		}
		if (answer == Integer.MAX_VALUE)
			answer = -1;
		out.println("Case " + testNumber + ": " + answer);
	}
}
