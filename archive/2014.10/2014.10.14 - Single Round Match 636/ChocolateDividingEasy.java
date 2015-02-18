package net.egork;

import net.egork.misc.ArrayUtils;

public class ChocolateDividingEasy {
    public int findBest(String[] chocolate) {
		int rowCount = chocolate.length;
		int columnCount = chocolate[0].length();
		int answer = 0;
		int[][] quality = new int[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				quality[i][j] = chocolate[i].charAt(j) - '0';
			}
		}
		long[][] sums = ArrayUtils.partialSums(quality);
		int[] rowDivs = new int[4];
		int[] colDivs = new int[4];
		rowDivs[3] = rowCount;
		colDivs[3] = columnCount;
		for (int i = 1; i < rowCount; i++) {
			rowDivs[1] = i;
			for (int j = 1; j < columnCount; j++) {
				colDivs[1] = j;
				for (int k = i + 1; k < rowCount; k++) {
					rowDivs[2] = k;
					for (int l = j + 1; l < columnCount; l++) {
						colDivs[2] = l;
						int min = Integer.MAX_VALUE;
						for (int m = 0; m < 3; m++) {
							for (int n = 0; n < 3; n++) {
								min = (int) Math.min(min, sums[rowDivs[m]][colDivs[n]] + sums[rowDivs[m + 1]][colDivs[n + 1]] -
									sums[rowDivs[m]][colDivs[n + 1]] - sums[rowDivs[m + 1]][colDivs[n]]);
							}
						}
						answer = Math.max(answer, min);
					}
				}
			}
		}
		return answer;
    }
}
