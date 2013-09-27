package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		boolean hasWhite = false;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == 'W')
					hasWhite = true;
			}
		}
		if (!hasWhite) {
			out.printLine(new Rational(rowCount + columnCount - 2, 1));
			return;
		}
		int[][] distance = new int[rowCount][columnCount];
		ArrayUtils.fill(distance, Integer.MAX_VALUE);
		distance[rowCount - 1][columnCount - 1] = 0;
		int[] rowQueue = new int[rowCount * columnCount];
		int[] columnQueue = new int[rowCount * columnCount];
		int size = 1;
		rowQueue[0] = rowCount - 1;
		columnQueue[0] = columnCount - 1;
		for (int i = 0; i < size; i++) {
			int row = rowQueue[i];
			int column = columnQueue[i];
			for (int j = 0; j < 4; j++) {
				int nRow = row + MiscUtils.DX4[j];
				int nColumn = column + MiscUtils.DY4[j];
				if (MiscUtils.isValidCell(nRow, nColumn, rowCount, columnCount) &&
					distance[nRow][nColumn] == Integer.MAX_VALUE && map[nRow][nColumn] == 'B')
				{
					distance[nRow][nColumn] = distance[row][column] + 1;
					rowQueue[size] = nRow;
					columnQueue[size++] = nColumn;
				}
			}
		}
		int distanceToWhite = Integer.MAX_VALUE;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == 'W') {
					distanceToWhite = Math.min(distanceToWhite, i + j);
				}
			}
		}
		Rational answer = new Rational(distance[0][0] - distanceToWhite, 1);
		int whiteCount = 0;
		int[] min = new int[rowCount * columnCount];
		boolean[] isAllBlack = new boolean[rowCount * columnCount];
		int remainingAllBlack = 0;
		size = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == 'W') {
					whiteCount++;
					int curDistance = Integer.MAX_VALUE;
					boolean allBlack = true;
					for (int k = 0; k < 4; k++) {
						int row = i + MiscUtils.DX4[k];
						int column = j + MiscUtils.DY4[k];
						if (MiscUtils.isValidCell(row, column, rowCount, columnCount)) {
							curDistance = Math.min(curDistance, distance[row][column]);
							if (map[row][column] == 'W')
								allBlack = false;
						}
					}
					min[size] = curDistance;
					isAllBlack[size++] = allBlack;
					if (allBlack)
						remainingAllBlack++;
				}
			}
		}
		min = Arrays.copyOf(min, size);
		isAllBlack = Arrays.copyOf(isAllBlack, size);
		int[] order = ArrayUtils.createOrder(size);
		final boolean[] finalIsAllBlack = isAllBlack;
		final int[] finalMin = min;
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				if (finalMin[first] != finalMin[second])
					return Integer.compare(finalMin[first], finalMin[second]);
				if (finalIsAllBlack[first] ^ finalIsAllBlack[second]) {
					if (finalIsAllBlack[first])
						return -1;
					return 1;
				}
				return 0;
			}
		});
		long sum = 0;
		int taken = 0;
		for (int i : order) {
			if (min[i] == Integer.MAX_VALUE)
				break;
			taken++;
			if (isAllBlack[i])
				remainingAllBlack--;
			sum += min[i];
			Rational candidate = new Rational(sum + (long)remainingAllBlack + (long)whiteCount, taken);
			if (BigInteger.valueOf(answer.numerator).multiply(BigInteger.valueOf(candidate.denominator)).compareTo(
				BigInteger.valueOf(candidate.numerator).multiply(BigInteger.valueOf(answer.denominator))) > 0)
			{
				answer = candidate;
			}
		}
		answer = answer.add(new Rational(distanceToWhite, 1));
		out.printLine(answer);
    }
}
