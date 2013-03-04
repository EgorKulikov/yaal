package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		final int[][] attractiveness = IOUtils.readIntTable(in, rowCount, columnCount);
		final int[] plainAttractiveness = new int[1024 * rowCount];
		int[][] profit = IOUtils.readIntTable(in, rowCount, columnCount);
		int[] plainProfit = new int[rowCount << 10];
		long[] answer = new long[rowCount << 10];
		int[] orderRightDown = new int[rowCount * columnCount];
		int[] orderLeftDown = new int[rowCount * columnCount];
		int[] valueLeftDown = new int[rowCount << 10];
		int[] valueRightDown = new int[rowCount << 10];
		int k = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				int index = (i << 10) + j;
				orderRightDown[k] = index;
				orderLeftDown[k] = index;
				plainAttractiveness[index] = attractiveness[i][j];
				plainProfit[index] = profit[i][j];
				valueLeftDown[index] = i - j;
				valueRightDown[index] = i + j;
				k++;
			}
		}
		IntComparator leftDownComparator = new IntComparator() {
			public int compare(int first, int second) {
				if (plainAttractiveness[first] != plainAttractiveness[second])
					return plainAttractiveness[first] - plainAttractiveness[second];
				int firstRow = first >> 10;
				int firstColumn = first & 1023;
				int secondRow = second >> 10;
				int secondColumn = second & 1023;
				return firstRow - firstColumn - secondRow + secondColumn;
			}
		};
		ArrayUtils.sort(orderLeftDown, leftDownComparator);
		IntComparator rightDownComparator = new IntComparator() {
			public int compare(int first, int second) {
				if (plainAttractiveness[first] != plainAttractiveness[second])
					return plainAttractiveness[first] - plainAttractiveness[second];
				int firstRow = first >> 10;
				int firstColumn = first & 1023;
				int secondRow = second >> 10;
				int secondColumn = second & 1023;
				return firstRow + firstColumn - (secondRow + secondColumn);
			}
		};
		ArrayUtils.sort(orderRightDown, rightDownComparator);
		int different = 0;
		if (plainAttractiveness[orderLeftDown[0]] != 0)
			different++;
		for (int i = 1; i < orderLeftDown.length; i++) {
			if (plainAttractiveness[orderLeftDown[i]] != plainAttractiveness[orderLeftDown[i - 1]])
				different++;
		}
		int[] starts = new int[different + 1];
		k = 0;
		if (plainAttractiveness[orderLeftDown[0]] != 0)
			starts[k++] = 0;
		for (int i = 1; i < orderLeftDown.length; i++) {
			if (plainAttractiveness[orderLeftDown[i]] != plainAttractiveness[orderLeftDown[i - 1]])
				starts[k++] = i;
		}
		starts[different] = rowCount * columnCount;
		for (int i = 0; i < different - 1; i++) {
			int j = starts[i];
			k = starts[i + 1];
			long max = Long.MIN_VALUE;
			int last = -5000;
			while (k < starts[i + 2]) {
				if (j < starts[i + 1] && valueLeftDown[orderLeftDown[j]] < valueLeftDown[orderLeftDown[k]]) {
					int current = (orderLeftDown[j] >> 10) - (orderLeftDown[j] & 1023);
					max += current - last;
					last = current;
					max = Math.max(max, answer[orderLeftDown[j]] + plainProfit[orderLeftDown[j]]);
					j++;
				} else {
					int current = (orderLeftDown[k] >> 10) - (orderLeftDown[k] & 1023);
					max += current - last;
					last = current;
					answer[orderLeftDown[k]] = Math.max(answer[orderLeftDown[k]], max);
					k++;
				}
			}
			j = starts[i];
			k = starts[i + 1];
			max = Long.MIN_VALUE;
			last = -5000;
			while (k < starts[i + 2]) {
				if (j < starts[i + 1] && valueRightDown[orderRightDown[j]] < valueRightDown[orderRightDown[k]]) {
					int current = (orderRightDown[j] >> 10) + (orderRightDown[j] & 1023);
					max += current - last;
					last = current;
					max = Math.max(max, answer[orderRightDown[j]] + plainProfit[orderRightDown[j]]);
					j++;
				} else {
					int current = (orderRightDown[k] >> 10) + (orderRightDown[k] & 1023);
					max += current - last;
					last = current;
					answer[orderRightDown[k]] = Math.max(answer[orderRightDown[k]], max);
					k++;
				}
			}
			j = starts[i + 1] - 1;
			k = starts[i + 2] - 1;
			max = Long.MIN_VALUE;
			last = -5000;
			while (k >= starts[i + 1]) {
				if (j >= starts[i] && valueRightDown[orderRightDown[j]] > valueRightDown[orderRightDown[k]]) {
					int current = -(orderRightDown[j] >> 10) - (orderRightDown[j] & 1023);
					max += current - last;
					last = current;
					max = Math.max(max, answer[orderRightDown[j]] + plainProfit[orderRightDown[j]]);
					j--;
				} else {
					int current = -(orderRightDown[k] >> 10) - (orderRightDown[k] & 1023);
					max += current - last;
					last = current;
					answer[orderRightDown[k]] = Math.max(answer[orderRightDown[k]], max);
					k--;
				}
			}
			j = starts[i + 1] - 1;
			k = starts[i + 2] - 1;
			max = Long.MIN_VALUE;
			last = -5000;
			while (k >= starts[i + 1]) {
				if (j >= starts[i] && valueLeftDown[orderLeftDown[j]] > valueLeftDown[orderLeftDown[k]]) {
					int current = -(orderLeftDown[j] >> 10) + (orderLeftDown[j] & 1023);
					max += current - last;
					last = current;
					max = Math.max(max, answer[orderLeftDown[j]] + plainProfit[orderLeftDown[j]]);
					j--;
				} else {
					int current = -(orderLeftDown[k] >> 10) + (orderLeftDown[k] & 1023);
					max += current - last;
					last = current;
					answer[orderLeftDown[k]] = Math.max(answer[orderLeftDown[k]], max);
					k--;
				}
			}
		}
		for (int i = 0; i < answer.length; i++)
			answer[i] += plainProfit[i];
		out.printLine(ArrayUtils.maxElement(answer));
	}
}
