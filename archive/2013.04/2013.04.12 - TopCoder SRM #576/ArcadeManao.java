package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;

public class ArcadeManao {
    public int shortestLadder(String[] level, int coinRow, int coinColumn) {
		int rowCount = level.length;
		int columnCount = level[0].length();
		char[][] map = new char[rowCount][];
		for (int i = 0; i < rowCount; i++)
			map[i] = level[i].toCharArray();
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(rowCount * columnCount);
		for (int i = 0; i < rowCount; i++) {
			for (int j = 1; j < columnCount; j++) {
				if (map[i][j] == 'X' && map[i][j - 1] == 'X')
					setSystem.join(i * columnCount + j, i * columnCount + j - 1);
			}
		}
		coinRow--;
		coinColumn--;
		for (int i = 1; i <= rowCount; i++) {
			if (setSystem.get((rowCount - 1) * columnCount) == setSystem.get(coinRow * columnCount + coinColumn))
				return i - 1;
			for (int j = i; j < rowCount; j++) {
				for (int k = 0; k < columnCount; k++) {
					if (map[j][k] == 'X' && map[j - i][k] == 'X')
						setSystem.join(j * columnCount + k, (j - i) * columnCount + k);
				}
			}
		}
		throw new RuntimeException();
    }
}
