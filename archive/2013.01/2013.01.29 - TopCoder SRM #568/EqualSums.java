package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;

import java.util.Arrays;

public class EqualSums {
	static final int MOD = (int) (1e9 + 7);

    public int count(String[] board) {
		int count = board.length;
		int[][] matrix = new int[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (board[i].charAt(j) == '-')
					matrix[i][j] = -1;
				else
					matrix[i][j] = board[i].charAt(j) - '0';
			}
		}
		IndependentSetSystem rows = new RecursiveIndependentSetSystem(count);
		IndependentSetSystem columns = new RecursiveIndependentSetSystem(count);
		int[][] deltaRows = new int[count][count];
		boolean[][] definedRows = new boolean[count][count];
		int[][] deltaColumns = new int[count][count];
		boolean[][] definedColumns = new boolean[count][count];
		int[] anyColumn = new int[count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (matrix[i][j] != -1)
					anyColumn[i] = j;
				for (int k = 0; k < count; k++) {
					if (matrix[i][j] != -1 && matrix[i][k] != -1) {
						if (definedColumns[j][k] && deltaColumns[j][k] != matrix[i][k] - matrix[i][j])
							return 0;
						definedColumns[j][k] = true;
						deltaColumns[j][k] = matrix[i][k] - matrix[i][j];
					}
					if (matrix[j][i] != -1 && matrix[k][i] != -1) {
						if (definedRows[j][k] && deltaRows[j][k] != matrix[k][i] - matrix[j][i])
							return 0;
						definedRows[j][k] = true;
						deltaRows[j][k] = matrix[k][i] - matrix[j][i];
					}
				}
			}
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++) {
					if (definedRows[j][i] && definedRows[i][k]) {
						if (definedRows[j][k] && deltaRows[j][k] != deltaRows[j][i] + deltaRows[i][k])
							return 0;
						definedRows[j][k] = true;
						deltaRows[j][k] = deltaRows[j][i] + deltaRows[i][k];
					}
					if (definedColumns[j][i] && definedColumns[i][k]) {
						if (definedColumns[j][k] && deltaColumns[j][k] != deltaColumns[j][i] + deltaColumns[i][k])
							return 0;
						definedColumns[j][k] = true;
						deltaColumns[j][k] = deltaColumns[j][i] + deltaColumns[i][k];
					}
				}
			}
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (matrix[i][j] == -1) {
					for (int k = 0; k < count && matrix[i][j] == -1; k++) {
						for (int l = 0; l < count && matrix[i][j] == -1; l++) {
							if (matrix[k][l] != -1 && definedRows[k][i] && definedColumns[l][j]) {
								matrix[i][j] = matrix[k][l] + deltaRows[k][i] + deltaColumns[l][j];
								if (matrix[i][j] < 0)
									return 0;
							}
						}
					}
				}
			}
		}
		long[] result = new long[10];
		int min = 10;
		boolean[] processed = new boolean[count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (definedRows[i][0] && definedColumns[j][anyColumn[0]]) {
					processed[i] = true;
					min = Math.min(min, matrix[i][j]);
				}
			}
		}
		result[min] = 1;
		long[] next = new long[10];
		for (int i = 0; i < count; i++) {
			if (processed[i])
				continue;
			Arrays.fill(next, 0);
			min = 10;
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++) {
					if (definedRows[i][j] && definedColumns[k][anyColumn[i]]) {
						processed[j] = true;
						min = Math.min(min, matrix[j][k]);
					}
				}
			}
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k <= j; k++)
					next[Math.min(j - k, min)] += result[j];
				for (int k = 1; k <= min; k++)
					next[Math.min(min - k, j)] += result[j];
			}
			for (int j = 0; j < 10; j++)
				result[j] = next[j] % MOD;
		}
		long answer = 0;
		for (long l : result)
			answer += l;
		return (int) (answer % MOD);
    }
}
