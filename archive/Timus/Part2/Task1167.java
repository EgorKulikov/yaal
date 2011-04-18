package Timus.Part2;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1167 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int horseCount = in.readInt();
		int stableCount = in.readInt();
		int[] horseType = in.readIntArray(horseCount);
		int[][] unhappiness = new int[horseCount][horseCount + 1];
		for (int i = 0; i < horseCount; i++) {
			int currentUnhappiness = 0;
			int whiteHorses = 0;
			int blackHorses = 0;
			for (int j = i; j < horseCount; j++) {
				if (horseType[j] == 0) {
					whiteHorses++;
					currentUnhappiness += blackHorses;
				} else {
					blackHorses++;
					currentUnhappiness += whiteHorses;
				}
				unhappiness[i][j + 1] = currentUnhappiness;
			}
		}
		int[][] totalUnhappiness = new int[horseCount + 1][stableCount + 1];
		for (int i = 1; i <= horseCount; i++) {
			totalUnhappiness[i][1] = unhappiness[0][i];
			for (int j = Math.max(2, i - horseCount + stableCount); j <= stableCount; j++) {
				totalUnhappiness[i][j] = Integer.MAX_VALUE / 2;
				for (int k = i - 1; k >= j - 1; k--)
					totalUnhappiness[i][j] = Math.min(totalUnhappiness[i][j], totalUnhappiness[k][j - 1] + unhappiness[k][i]);
			}
		}
		out.println(totalUnhappiness[horseCount][stableCount]);
	}
}

