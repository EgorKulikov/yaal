package April2011.UVaHugeEasyContestII;

import net.egork.collections.ArrayUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskY implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		if (testNumber != 1)
			out.println();
		int ballCount = in.readInt();
		int ticketCount = in.readInt();
		int[] balls = in.readIntArray(ballCount);
		int cornerValue = in.readInt();
		int midLineValue = in.readInt();
		int diagonalValue = in.readInt();
		int winValue = in.readInt();
		int[] cornersRemaining = new int[ticketCount];
		int[] midLineRemaining = new int[ticketCount];
		int[] diagonalRemaining = new int[ticketCount];
		int[] winRemaining = new int[ticketCount];
		Arrays.fill(cornersRemaining, 4);
		Arrays.fill(midLineRemaining, 5);
		Arrays.fill(diagonalRemaining, 9);
		Arrays.fill(winRemaining, 25);
		int[][] row = new int[ticketCount][76];
		int[][] column = new int[ticketCount][76];
		ArrayUtils.fill(row, -1);
		ArrayUtils.fill(column, -1);
		for (int i = 0; i < ticketCount; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					int number = in.readInt();
					row[i][number] = j;
					column[i][number] = k;
				}
			}
		}
		int[] value = new int[ticketCount];
		for (int i = 0; i < ballCount; i++) {
//			boolean won = false;
			for (int j = 0; j < ticketCount; j++) {
				if (row[j][balls[i]] == -1)
					continue;
				int r = row[j][balls[i]];
				int c = column[j][balls[i]];
				winRemaining[j]--;
				if (winRemaining[j] == 0) {
//					won = true;
					value[j] += winValue;
				}
				if ((r == 0 || r == 4) && (c == 0 || c == 4)) {
					cornersRemaining[j]--;
					if (cornersRemaining[j] == 0 && i < 35)
						value[j] += cornerValue;
				}
				if (r == 2) {
					midLineRemaining[j]--;
					if (midLineRemaining[j] == 0 && i < 40)
						value[j] += midLineValue;
				}
				if (c == r || c + r == 4) {
					diagonalRemaining[j]--;
					if (diagonalRemaining[j] == 0 && i < 45)
						value[j] += diagonalValue;
				}
			}
//			if (won)
//				break;
		}
		out.println("Case " + testNumber + ":");
		for (int amount : value)
			out.println(amount);
	}
}

