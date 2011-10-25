import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Game implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		char[][] table = IOUtils.readTable(in, 8, 8);
		int answer = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i != 7) {
					char temp = table[i][j];
					table[i][j] = table[i + 1][j];
					table[i + 1][j] = temp;
					answer = Math.max(answer, count(table));
					temp = table[i][j];
					table[i][j] = table[i + 1][j];
					table[i + 1][j] = temp;
				}
				if (j != 7) {
					char temp = table[i][j];
					table[i][j] = table[i][j + 1];
					table[i][j + 1] = temp;
					answer = Math.max(answer, count(table));
					temp = table[i][j];
					table[i][j] = table[i][j + 1];
					table[i][j + 1] = temp;
				}
			}
		}
		out.println(answer);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i != 7) {
					char temp = table[i][j];
					table[i][j] = table[i + 1][j];
					table[i + 1][j] = temp;
					if (count(table) == answer)
						out.println(i + " " + j + " D");
					temp = table[i][j];
					table[i][j] = table[i + 1][j];
					table[i + 1][j] = temp;
				}
				if (j != 7) {
					char temp = table[i][j];
					table[i][j] = table[i][j + 1];
					table[i][j + 1] = temp;
					if (count(table) == answer)
						out.println(i + " " + j + " R");
					temp = table[i][j];
					table[i][j] = table[i][j + 1];
					table[i][j + 1] = temp;
				}
			}
		}
	}

	private int count(char[][] table) {
		Set<Pair<Integer, Integer>> result = new HashSet<Pair<Integer, Integer>>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i == 0 || table[i - 1][j] != table[i][j]) {
					int length = 1;
					for (int k = 1; i + k < 8; k++) {
						if (table[i][j] != table[i + k][j])
							break;
						length++;
					}
					if (length >= 3) {
						for (int k = 0; k < length; k++)
							result.add(Pair.makePair(i + k, j));
					}
				}
				if (j == 0 || table[i][j - 1] != table[i][j]) {
					int length = 1;
					for (int k = 1; j + k < 8; k++) {
						if (table[i][j] != table[i][j + k])
							break;
						length++;
					}
					if (length >= 3) {
						for (int k = 0; k < length; k++)
							result.add(Pair.makePair(i, j + k));
					}
				}
			}
		}
		return result.size();
	}
}

