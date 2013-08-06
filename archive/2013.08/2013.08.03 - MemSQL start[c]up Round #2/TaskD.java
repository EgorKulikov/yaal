package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[][] filled = new int[3000][3000];
		boolean[][] isLeft = new boolean[3000][3000];
		boolean[][] isBottom = new boolean[3000][3000];
		boolean[][] isRight = new boolean[3000][3000];
		boolean[][] isTop = new boolean[3000][3000];
		int[] x = new int[count];
		int[] y = new int[count];
		int[] x2 = new int[count];
		int[] y2 = new int[count];
		for (int i = 0; i < count; i++) {
			int x0 = in.readInt();
			int y0 = in.readInt();
			int x1 = in.readInt();
			int y1 = in.readInt();
			x[i] = x0;
			y[i] = y0;
			x2[i] = x1;
			y2[i] = y1;
			for (int j = x0; j < x1; j++) {
				for (int k = y0; k < y1; k++)
					filled[j][k] = 1;
				isLeft[j][y0] = true;
				isRight[j][y1 - 1] = true;
			}
			for (int j = y0; j < y1; j++) {
				isBottom[x0][j] = true;
				isTop[x1 - 1][j] = true;
			}
		}
		int[][] totalFilled = new int[3001][3001];
		for (int i = 0; i < 3000; i++) {
			for (int j = 0; j < 3000; j++)
				totalFilled[i + 1][j + 1] = totalFilled[i + 1][j] + totalFilled[i][j + 1] - totalFilled[i][j] + filled[i][j];
		}
		int[][] max = new int[3000][3000];
		for (int i = 0; i < count; i++) {
			int xx = x[i];
			int yy = y[i];
			for (int j = 1; xx + j <= 3000 && yy + j <= 3000; j++) {
				int curFilled = totalFilled[xx + j][yy + j] + totalFilled[xx][yy] - totalFilled[xx + j][yy] - totalFilled[xx][yy + j];
				if (curFilled != j * j || !isLeft[xx + j - 1][yy] || !isBottom[xx][yy + j - 1])
					break;
				max[xx][yy] = j;
			}
		}
		for (int i = 0; i < count; i++) {
			int xx = x2[i];
			int yy = y2[i];
			for (int j = 1; j <= xx && j <= yy; j++) {
				int curFilled = totalFilled[xx - j][yy - j] + totalFilled[xx][yy] - totalFilled[xx - j][yy] - totalFilled[xx][yy - j];
				if (curFilled != j * j || !isRight[xx - j][yy - 1] || !isTop[xx - 1][yy - j])
					break;
				if (max[xx - j][yy - j] >= j) {
					IntList answer = new IntArrayList();
					for (int k = 0; k < count; k++) {
						if (x[k] >= xx - j && x[k] < xx && y[k] >= yy - j && y[k] < yy)
							answer.add(k + 1);
					}
					out.printLine("YES", answer.size());
					out.printLine(answer);
					return;
				}
			}
		}
		out.printLine("NO");
    }
}
