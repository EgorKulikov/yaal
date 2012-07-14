package April2011.UVaHugeEasyContestII;

import net.egork.collections.Pair;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class TaskQ implements Solver {
	private static final int[] DX = {0, 1, 1, 1};
	private static final int[] DY = {1, -1, 0, 1};

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int moveCount = in.readInt();
		int winningLine = in.readInt();
		Set<Pair<Integer, Integer>> crosses = new HashSet<Pair<Integer, Integer>>();
		Set<Pair<Integer, Integer>> noughts = new HashSet<Pair<Integer, Integer>>();
		for (int i = 0; i < moveCount; i++) {
			int x = in.readInt();
			int y = in.readInt();
			if ((i & 1) == 0)
				crosses.add(new Pair<Integer, Integer>(x, y));
			else
				noughts.add(new Pair<Integer, Integer>(x, y));
		}
		boolean isCrossesWon = false;
		boolean isNoughtsWon = false;
		for (Pair<Integer, Integer> cross : crosses) {
			for (int i = 0; i < 4; i++) {
				boolean line = true;
				for (int j = 1; j < winningLine; j++) {
					if (!crosses.contains(new Pair<Integer, Integer>(cross.first() + j * DX[i], cross.second() + j * DY[i]))) {
						line = false;
						break;
					}
				}
				if (line) {
					isCrossesWon = true;
					break;
				}
			}
			if (isCrossesWon)
				break;
		}
		for (Pair<Integer, Integer> nought : noughts) {
			for (int i = 0; i < 4; i++) {
				boolean line = true;
				for (int j = 1; j < winningLine; j++) {
					if (!noughts.contains(new Pair<Integer, Integer>(nought.first() + j * DX[i], nought.second() + j * DY[i]))) {
						line = false;
						break;
					}
				}
				if (line) {
					isNoughtsWon = true;
					break;
				}
			}
			if (isNoughtsWon)
				break;
		}
		out.print("Case " + testNumber + ": ");
		if (isCrossesWon) {
			if (isNoughtsWon)
				out.println("error");
			else
				out.println("crosses");
		} else {
			if (isNoughtsWon)
				out.println("noughts");
			else
				out.println("none");
		}
	}
}

