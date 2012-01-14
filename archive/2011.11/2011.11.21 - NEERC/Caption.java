package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class Caption {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		final int width = in.readInt();
		int minSpacing = in.readInt();
		int maxSpacing = in.readInt();
		int[] letterIndex = new int[256];
		Arrays.fill(letterIndex, -1);
		List<char[]> firstRow = new ArrayList<char[]>();
		char[] current;
		int index = 0;
		while (true) {
			current = in.readString().toCharArray();
			if (current.length == 1) {
				firstRow.add(in.readString().toCharArray());
				letterIndex[current[0]] = index++;
			} else
				break;
		}
		letterIndex[' '] = index++;
		char[][][] letters = new char[index][rowCount][];
		for (int i = 0; i < index - 1; i++)
			letters[i][0] = firstRow.get(i);
		letters[0][1] = current;
		for (int i = 1; i < rowCount; i++) {
			for (int j = (i == 1 ? 1 : 0); j < index - 1; j++)
				letters[j][i] = in.readString().toCharArray();
		}
		for (int i = 0; i < rowCount; i++) {
			letters[index - 1][i] = new char[width];
			Arrays.fill(letters[index - 1][i], '.');
		}
		int[][] letterMask = new int[256][];
		for (int i = 0; i < 256; i++) {
			if (letterIndex[i] == -1)
				continue;
			letterMask[i] = new int[rowCount];
			for (int j = 0; j < rowCount; j++) {
				for (int k = 0; k < width; k++) {
					if (letters[letterIndex[i]][j][k] == '*')
						letterMask[i][j] += 1 << k;
				}
			}
		}
		char[] was = in.readLine().toCharArray();
		int[] spacing = new int[was.length];
		for (int i = 0; i < spacing.length; i++)
			spacing[i] = in.readInt();
		char[] need = in.readLine().toCharArray();
		char[][] image = new char[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++)
			Arrays.fill(image[i], '.');
		int position = 0;
		for (int i = 0; i < was.length; i++) {
			position += spacing[i];
			for (int j = 0; j < rowCount; j++) {
				System.arraycopy(letters[letterIndex[was[i]]][j], 0, image[j], position, width);
			}
			position += width;
		}
		final int[] freeCost = new int[columnCount + 1];
		for (int i = 0; i < columnCount; i++) {
			freeCost[i + 1] = freeCost[i];
			for (int j = 0; j < rowCount; j++) {
				if (image[j][i] == '*')
					freeCost[i + 1]++;
			}
		}
		int[][] imageMask = new int[rowCount][columnCount - width + 1];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j <= columnCount - width; j++) {
				for (int k = 0; k < width; k++) {
					if (image[i][j + k] == '*')
						imageMask[i][j] += 1 << k;
				}
			}
		}
		int[][] letterCost = new int[256][columnCount - width + 1];
		for (int i = 0; i < 256; i++) {
			if (letterIndex[i] == -1)
				continue;
			for (int j = 0; j <= columnCount - width; j++) {
				for (int k = 0; k < rowCount; k++) {
					letterCost[i][j] += Integer.bitCount(letterMask[i][k] ^ imageMask[k][j]);
				}
			}
		}
		final int[][] result = new int[need.length][columnCount - width + 1];
		int[][] last = new int[need.length][columnCount - width + 1];
		for (int i = 0; i <= columnCount - width; i++)
			result[0][i] = freeCost[i] + letterCost[need[0]][i];
		for (int i = 1; i < need.length; i++) {
			final int finalI = i;
			NavigableSet<Integer> queue = new TreeSet<Integer>(new Comparator<Integer>() {
				public int compare(Integer o1, Integer o2) {
					int value = result[finalI - 1][o1] - freeCost[o1 + width] - result[finalI - 1][o2] + freeCost[o2 + width];
					if (value != 0)
						return value;
					return o1 - o2;
				}
			});
			for (int j = 0; j <= columnCount - width; j++) {
				index = j - minSpacing - width;
				if (index >= 0 && result[i - 1][index] != Integer.MAX_VALUE)
					queue.add(index);
				if (queue.isEmpty())
					result[i][j] = Integer.MAX_VALUE;
				else {
					int head = queue.first();
					result[i][j] = result[i - 1][head] - freeCost[head + width] + freeCost[j] + letterCost[need[i]][j];
					last[i][j] = head;
				}
				index = j - maxSpacing - width;
				if (index >= 0 && result[i - 1][index] != Integer.MAX_VALUE)
					queue.remove(index);
			}
		}
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for (int i = 0; i <= columnCount - width; i++) {
			if (result[need.length - 1][i] == Integer.MAX_VALUE)
				continue;
			result[need.length - 1][i] += freeCost[columnCount] - freeCost[i + width];
			if (min > result[need.length - 1][i]) {
				min = result[need.length - 1][i];
				minIndex = i;
			}
		}
		int[] newPosition = new int[need.length];
		newPosition[need.length - 1] = minIndex;
		for (int i = need.length - 1; i > 0; i--) {
			minIndex = last[i][minIndex];
			newPosition[i - 1] = minIndex;
		}
		for (int i = need.length - 1; i > 0; i--)
			newPosition[i] -= newPosition[i - 1] + width;
		out.print(newPosition[0]);
		for (int i = 1; i < need.length; i++)
			out.print("", newPosition[i]);
		out.printLine();
	}
}
