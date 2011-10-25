import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class CollectTheChocolateChips implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int size = in.readInt();
		int[][] board = new int[size][];
		for (int i = 0; i < size; i++)
			board[i] = IOUtils.readIntArray(in, i + 1);
		int[][] po = new int[size][];
		po[0] = new int[1];
		po[0][0] = board[0][0];
		for (int i = 1; i < size; i++) {
			po[i] = new int[i + 1];
			for (int j = 0; j <= i && i + j < size - 1; j++) {
				po[i][j] = 0;
				if (j > 0)
					po[i][j] = Math.max(po[i][j], po[i - 1][j - 1]);
				if (j < po[i - 1].length)
					po[i][j] = Math.max(po[i][j], po[i - 1][j]);
				if (j + 1 < po[i - 1].length)
					po[i][j] = Math.max(po[i][j], po[i - 1][j + 1]);
				po[i][j] += board[i][j];
			}
		}
		po[size - 1][size - 1] = board[size - 1][size - 1];
		for (int i = size - 2; i >= 0; i--) {
			for (int j = size - 1; j >= i && i + j >= size; j--) {
				po[j][i] = 0;
				if (j < size - 1)
					po[j][i] = Math.max(po[j][i], po[j + 1][i + 1]);
				if (po[j].length > i + 1)
					po[j][i] = Math.max(po[j][i], po[j][i + 1]);
				if (po[j - 1].length > i + 1)
					po[j][i] = Math.max(po[j][i], po[j - 1][i + 1]);
				po[j][i] += board[j][i];
			}
		}
		int result = board[size - 1][0] + po[size - 2][0] + po[size - 1][1];
		int up = po[size - 2][0];
		int right = po[size - 1][1];
		int diagonal = board[size - 1][0];
		for (int i = 1; po[size - i - 1].length > i; i++) {
			int curUp = po[size - i - 2][i - 1];
			if (po[size - i - 2].length > i)
				curUp = Math.max(curUp, po[size - i - 2][i]);
			int curRight = po[size - i][i + 1];
			if (po[size - i - 1].length > i + 1)
				curRight = Math.max(curRight, po[size - i - 1][i + 1]);
			up = Math.max(up, curUp);
			right = Math.max(right, curRight);
			diagonal += board[size - i - 1][i];
			result = Math.max(result, up + curRight + diagonal);
			result = Math.max(result, right + curUp + diagonal);
		}
		out.println(result);
	}
}

