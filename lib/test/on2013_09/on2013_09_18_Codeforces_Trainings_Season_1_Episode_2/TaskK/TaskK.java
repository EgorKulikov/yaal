package on2013_09.on2013_09_18_Codeforces_Trainings_Season_1_Episode_2.TaskK;



import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskK {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		String[] rows = in.readString().split("/");
		int[][] board = new int[8][8];
		for (int i = 0; i < 8; i++) {
			int k = 0;
			for (int j = 0; j < rows[i].length(); j++) {
				if (Character.isDigit(rows[i].charAt(j)))
					k += rows[i].charAt(j) - '0';
				else {
					char c = rows[i].charAt(j);
					if (Character.toLowerCase(c) == 'p')
						board[i][k] = Character.isLowerCase(c) ? -1 : 1;
					if (Character.toLowerCase(c) == 'n')
						board[i][k] = 2;
					if (Character.toLowerCase(c) == 'b')
						board[i][k] = 3;
					if (Character.toLowerCase(c) == 'r')
						board[i][k] = 4;
					if (Character.toLowerCase(c) == 'q')
						board[i][k] = 5;
					if (Character.toLowerCase(c) == 'k')
						board[i][k] = 6;
					k++;
				}
			}
		}
		boolean[][] bad = new boolean[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != 0) {
					bad[i][j] = true;
					if (board[i][j] == -1) {
						if (j > 0 && i < 7)
							bad[i + 1][j - 1] = true;
						if (j < 7 && i < 7)
							bad[i + 1][j + 1] = true;
					} else if (board[i][j] == 1) {
						if (j > 0 && i > 0)
							bad[i - 1][j - 1] = true;
						if (j < 7 && i > 0)
							bad[i - 1][j + 1] = true;
					} else if (board[i][j] == 2) {
						for (int k = 0; k < 8; k++) {
							int ii = i + MiscUtils.DX_KNIGHT[k];
							int jj = j + MiscUtils.DY_KNIGHT[k];
							if (MiscUtils.isValidCell(ii, jj, 8, 8))
								bad[ii][jj] = true;
						}
					} else if (board[i][j] == 3) {
						for (int k = -1; k <= 1; k += 2) {
							for (int l = -1; l <= 1; l += 2) {
								int ii = i + k;
								int jj = j + l;
								while (MiscUtils.isValidCell(ii, jj, 8, 8) && board[ii][jj] == 0) {
									bad[ii][jj] = true;
									ii += k;
									jj += l;
								}
							}
						}
					} else if (board[i][j] == 4) {
						for (int k = 0; k < 4; k++) {
							int ii = i + MiscUtils.DX4[k];
							int jj = j + MiscUtils.DY4[k];
							while (MiscUtils.isValidCell(ii, jj, 8, 8) && board[ii][jj] == 0) {
								bad[ii][jj] = true;
								ii += MiscUtils.DX4[k];
								jj += MiscUtils.DY4[k];
							}
						}
					} else if (board[i][j] == 5) {
						for (int k = 0; k < 8; k++) {
							int ii = i + MiscUtils.DX8[k];
							int jj = j + MiscUtils.DY8[k];
							while (MiscUtils.isValidCell(ii, jj, 8, 8) && board[ii][jj] == 0) {
								bad[ii][jj] = true;
								ii += MiscUtils.DX8[k];
								jj += MiscUtils.DY8[k];
							}
						}
					} else {
						for (int k = 0; k < 8; k++) {
							int ii = i + MiscUtils.DX8[k];
							int jj = j + MiscUtils.DY8[k];
							if (MiscUtils.isValidCell(ii, jj, 8, 8))
								bad[ii][jj] = true;
						}
					}
				}
			}
		}
		int answer = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (!bad[i][j])
					answer++;
			}
		}
		out.printLine(answer);
	}
}
