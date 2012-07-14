package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[][] forwardRow = new int[7][7];
		int[][] forwardUp = new int[7][7];
		int[][] forwardRight = new int[7][7];
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 6; j++) {
				if (i == j)
					continue;
				int up = i;
				int right = j;
				int result = up;
				int moveCount = columnCount - 1;
				result += (moveCount / 4) * 14;
				moveCount %= 4;
				for (int k = 0; k < moveCount; k++) {
					int nextUp = 7 - right;
					right = up;
					up = nextUp;
					result += nextUp;
				}
				forwardRow[i][j] = result;
				forwardUp[i][j] = up;
				forwardRight[i][j] = right;
			}
		}
		int[][] backwardRow = new int[7][7];
		int[][] backwardUp = new int[7][7];
		int[][] backwardRight = new int[7][7];
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 6; j++) {
				if (i == j)
					continue;
				int up = i;
				int right = j;
				int result = up;
				int moveCount = columnCount - 1;
				result += (moveCount / 4) * 14;
				moveCount %= 4;
				for (int k = 0; k < moveCount; k++) {
					int nextRight = 7 - up;
					up = right;
					right = nextRight;
					result += up;
				}
				backwardRow[i][j] = result;
				backwardUp[i][j] = up;
				backwardRight[i][j] = right;
			}
		}
		int up = 1;
		int right = 3;
		int front = 2;
		boolean forward = true;
		long answer = 0;
		for (int i = 0; i < rowCount; i++) {
			if (forward) {
				answer += forwardRow[up][right];
				int nextUp = forwardUp[up][right];
				int nextRight = forwardRight[up][right];
				up = nextUp;
				right = nextRight;
			} else {
				answer += backwardRow[up][right];
				int nextUp = backwardUp[up][right];
				int nextRight = backwardRight[up][right];
				up = nextUp;
				right = nextRight;
			}
			int nextUp = 7 - front;
			front = up;
			up = nextUp;
			forward = !forward;
		}
		out.printLine(answer);
	}
}
