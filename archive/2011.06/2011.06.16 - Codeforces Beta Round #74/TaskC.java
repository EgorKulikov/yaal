import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] field = IOUtils.readTable(in, rowCount, columnCount);
		int answer = 0;
		int count = 0;
		Node[][] sample = new Node[rowCount][columnCount];
		Node[][] table = new Node[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (field[i][j] == '.')
					continue;
				int left = -1;
				for (int k = j - 1; k >= 0; k--) {
					if (field[i][k] != '.') {
						left = k;
						break;
					}
				}
				int right = -1;
				for (int k = j + 1; k < columnCount; k++) {
					if (field[i][k] != '.') {
						right = k;
						break;
					}
				}
				int up = -1;
				for (int k = i - 1; k >= 0; k--) {
					if (field[k][j] != '.') {
						up = k;
						break;
					}
				}
				int down = -1;
				for (int k = i + 1; k < rowCount; k++) {
					if (field[k][j] != '.') {
						down = k;
						break;
					}
				}
				sample[i][j] = new Node(up, down, left, right);
				table[i][j] = new Node(up, down, left, right);
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (field[i][j] == '.')
					continue;
				for (int k = 0; k < rowCount; k++) {
					for (int l = 0; l < columnCount; l++) {
						if (table[k][l] != null)
							table[k][l].fix(sample[k][l]);
					}
				}
				int score = 0;
				int row = i;
				int column = j;
				while (row != -1 && column != -1) {
					score++;
					Node node = table[row][column];
					if (node.left != -1)
						table[row][node.left].right = node.right;
					if (node.right != -1)
						table[row][node.right].left = node.left;
					if (node.up != -1)
						table[node.up][column].down = node.down;
					if (node.down != -1)
						table[node.down][column].up = node.up;
					switch (field[row][column]) {
						case 'L': {
							column = node.left;
							break;
						}
						case 'R': {
							column = node.right;
							break;
						}
						case 'D': {
							row = node.down;
							break;
						}
						default: {
							row = node.up;
							break;
						}
					}
				}
				if (score > answer) {
					answer = score;
					count = 1;
				} else if (score == answer)
					count++;
			}
		}
		out.println(answer + " " + count);
	}

	private static class Node {
		private int up;
		private int down;
		private int left;
		private int right;

		private Node(int up, int down, int left, int right) {
			this.up = up;
			this.down = down;
			this.left = left;
			this.right = right;
		}

		private void fix(Node node) {
			left = node.left;
			right = node.right;
			up = node.up;
			down = node.down;
		}
	}
}

