import net.egork.utils.Solver;

import java.io.PrintWriter;

public class BickeringCooks implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int[][][] input = readInput(in);
		int[][] disruption = input[0];
		int[][] collaboration = input[1];
		int cookCount = disruption.length;
		int firstBase = -1;
		int secondBase = -1;
		for (int i = 0; firstBase == -1 && i < cookCount; i++) {
			for (int j = i + 1; j < cookCount; j++) {
				if (disruption[i][j] != 0) {
					firstBase = i;
					secondBase = j;
					break;
				}
			}
		}
		boolean[] side = new boolean[cookCount];
		side[firstBase] = true;
		for (int i = 0; i < cookCount; i++) {
			if (firstBase != i && secondBase != i)
				side[i] = (i & 1) == 0;
		}
		int[] deltaDisruption = new int[cookCount];
		int[] deltaCollaboration = new int[cookCount];
		long totalDisruption = 0;
		long totalCollaboration = 0;
		for (int i = 0; i < cookCount; i++) {
			for (int j = i + 1; j < cookCount; j++) {
				if (side[i] != side[j]) {
					deltaDisruption[i] += disruption[i][j];
					deltaDisruption[j] += disruption[i][j];
					deltaCollaboration[i] += collaboration[i][j];
					deltaCollaboration[j] += collaboration[i][j];
					totalDisruption += disruption[i][j];
					totalCollaboration += collaboration[i][j];
				} else {
					deltaDisruption[i] -= disruption[i][j];
					deltaDisruption[j] -= disruption[i][j];
					deltaCollaboration[i] -= collaboration[i][j];
					deltaCollaboration[j] -= collaboration[i][j];
				}
			}
		}
		for (int i = 0; i < 5 * cookCount; i++) {
			int index = -1;
			long initialDisruption = totalDisruption;
			long initialCollaboration = totalCollaboration;
			for (int j = 0; j < cookCount; j++) {
				if (totalDisruption * (initialCollaboration + deltaCollaboration[j]) > totalCollaboration * (initialDisruption + deltaDisruption[j])) {
					index = j;
					totalDisruption = initialDisruption + deltaDisruption[j];
					totalCollaboration = initialCollaboration + deltaCollaboration[j];
				}
			}
			if (index == -1)
				break;
			side[index] = !side[index];
			for (int j = 0; j < cookCount; j++) {
				if (index == j)
					continue;
				if (side[index] == side[j]) {
					deltaDisruption[index] -= 2 * disruption[index][j];
					deltaDisruption[j] -= 2 * disruption[index][j];
					deltaCollaboration[index] -= 2 * collaboration[index][j];
					deltaCollaboration[j] -= 2 * collaboration[index][j];
				} else {
					deltaDisruption[index] += 2 * disruption[index][j];
					deltaDisruption[j] += 2 * disruption[index][j];
					deltaCollaboration[index] += 2 * collaboration[index][j];
					deltaCollaboration[j] += 2 * collaboration[index][j];
				}
			}
		}
		int leftSideSize = 0;
		for (int i = 0; i < cookCount; i++) {
			if (side[i])
				leftSideSize++;
		}
		out.print(leftSideSize);
		for (int i = 0; i < cookCount; i++) {
			if (side[i])
				out.print(" " + (i + 1));
		}
		out.println();
	}

	public static int[][][] readInput(net.egork.utils.old.io.old.InputReader in) {
		int cookCount = in.readInt();
		int disruptivePairCount = in.readInt();
		int collaborativePairCount = in.readInt();
		int[][] disruption = new int[cookCount][cookCount];
		for (int i = 0; i < disruptivePairCount; i++) {
			int first = in.readInt() - 1;
			int second = in.readInt() - 1;
			disruption[second][first] = disruption[first][second] = in.readInt();
		}
		int[][] collaboration = new int[cookCount][cookCount];
		for (int i = 0; i < collaborativePairCount; i++) {
			int first = in.readInt() - 1;
			int second = in.readInt() - 1;
			collaboration[second][first] = collaboration[first][second] = in.readInt();
		}
		return new int[][][]{disruption, collaboration};
	}
}

