import net.egork.collections.Pair;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class TaskC implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int red = in.readInt();
		int black = in.readInt();
		if (red == 1 && black == 2) {
			out.println(2);
			out.println("NN");
			return;
		}
		int row = 0;
		int column = 0;
		Set<Pair<Integer, Integer>> visited = new HashSet<Pair<Integer, Integer>>();
		StringBuilder answer = new StringBuilder();
		if (red < black - 1) {
			visited.add(Pair.makePair(0, -1));
			answer.append('N');
			int temp = red;
			red = black - 1;
			black = temp;
		}
		black--;
		visited.add(Pair.makePair(0, 0));
		Pair<Integer, Integer> down = Pair.makePair(0, -1);
		if (red != 0 && !visited.contains(down)) {
			answer.append("SN");
			visited.add(down);
			red--;
		}
		while (red != 0) {
			visited.add(Pair.makePair(row - 1, column));
			red--;
			answer.append("WE");
			if (red == 0)
				break;
			visited.add(Pair.makePair(row + 1, column));
			red--;
			answer.append("EW");
			if (red == 0)
				break;
			visited.add(Pair.makePair(row, column + 1));
			red--;
			answer.append("NS");
			if (red == 0)
				break;
			column += 2;
			visited.add(Pair.makePair(row, column));
			answer.append("NN");
			black--;
		}
		while (black != 0) {
			if (visited.contains(Pair.makePair(row - 1, column))) {
				answer.append("W");
				row--;
				if (!visited.contains(Pair.makePair(row - 1, column))) {
					answer.append("WE");
					black--;
					visited.add(Pair.makePair(row - 1, column));
					if (black == 0)
						break;
				}
				if (!visited.contains(Pair.makePair(row + 1, column))) {
					answer.append("EW");
					black--;
					visited.add(Pair.makePair(row + 1, column));
					if (black == 0)
						break;
				}
				if (!visited.contains(Pair.makePair(row, column - 1))) {
					answer.append("SN");
					black--;
					visited.add(Pair.makePair(row, column - 1));
					if (black == 0)
						break;
				}
				if (!visited.contains(Pair.makePair(row, column + 1))) {
					answer.append("NS");
					black--;
					visited.add(Pair.makePair(row, column + 1));
					if (black == 0)
						break;
				}
				row++;
				answer.append("E");
			}
			if (visited.contains(Pair.makePair(row + 1, column))) {
				answer.append("E");
				row++;
				if (!visited.contains(Pair.makePair(row - 1, column))) {
					answer.append("WE");
					black--;
					visited.add(Pair.makePair(row - 1, column));
					if (black == 0)
						break;
				}
				if (!visited.contains(Pair.makePair(row + 1, column))) {
					answer.append("EW");
					black--;
					visited.add(Pair.makePair(row + 1, column));
					if (black == 0)
						break;
				}
				if (!visited.contains(Pair.makePair(row, column - 1))) {
					answer.append("SN");
					black--;
					visited.add(Pair.makePair(row, column - 1));
					if (black == 0)
						break;
				}
				if (!visited.contains(Pair.makePair(row, column + 1))) {
					answer.append("NS");
					black--;
					visited.add(Pair.makePair(row, column + 1));
					if (black == 0)
						break;
				}
				row--;
				answer.append("W");
			}
			if (visited.contains(Pair.makePair(row, column - 1))) {
				answer.append("S");
				column--;
				if (!visited.contains(Pair.makePair(row - 1, column))) {
					answer.append("WE");
					black--;
					visited.add(Pair.makePair(row - 1, column));
					if (black == 0)
						break;
				}
				if (!visited.contains(Pair.makePair(row + 1, column))) {
					answer.append("EW");
					black--;
					visited.add(Pair.makePair(row + 1, column));
					if (black == 0)
						break;
				}
				if (!visited.contains(Pair.makePair(row, column - 1))) {
					answer.append("SN");
					black--;
					visited.add(Pair.makePair(row, column - 1));
					if (black == 0)
						break;
				}
				if (!visited.contains(Pair.makePair(row, column + 1))) {
					answer.append("NS");
					black--;
					visited.add(Pair.makePair(row, column + 1));
					if (black == 0)
						break;
				}
				column++;
				answer.append("N");
			}
			if (visited.contains(Pair.makePair(row, column + 1))) {
				answer.append("N");
				column++;
				if (!visited.contains(Pair.makePair(row - 1, column))) {
					answer.append("WE");
					black--;
					visited.add(Pair.makePair(row - 1, column));
					if (black == 0)
						break;
				}
				if (!visited.contains(Pair.makePair(row + 1, column))) {
					answer.append("EW");
					black--;
					visited.add(Pair.makePair(row + 1, column));
					if (black == 0)
						break;
				}
				if (!visited.contains(Pair.makePair(row, column - 1))) {
					answer.append("SN");
					black--;
					visited.add(Pair.makePair(row, column - 1));
					if (black == 0)
						break;
				}
				if (!visited.contains(Pair.makePair(row, column + 1))) {
					answer.append("NS");
					black--;
					visited.add(Pair.makePair(row, column + 1));
					if (black == 0)
						break;
				}
				column--;
				answer.append("S");
			}
			answer.append("SS");
			column -= 2;
		}
		out.println(answer.length());
		out.println(answer);
	}
}

