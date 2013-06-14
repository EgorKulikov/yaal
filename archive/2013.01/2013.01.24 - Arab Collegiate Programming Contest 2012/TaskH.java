package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int firstSize = in.readInt();
		char[][] first = IOUtils.readTable(in, firstSize, firstSize);
		if (!fix(first)) {
			out.printLine("NO");
			return;
		}
		int secondSize = in.readInt();
		char[][] second = IOUtils.readTable(in, secondSize, secondSize);
		if (!fix(second)) {
			out.printLine("NO");
			return;
		}
		out.printLine("YES");
		for (int i = 0; i < firstSize; i++) {
			out.print(new String(first[i]));
			for (int j = 0; j < secondSize; j++)
				out.print('1');
			out.printLine();
		}
		for (int i = 0; i < secondSize; i++) {
			for (int j = 0; j < firstSize; j++)
				out.print('1');
			out.printLine(second[i]);
		}
	}

	private boolean fix(char[][] distance) {
		int size = distance.length;
		int current = 0;
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if (distance[i][j] == 'x')
					distance[j][i] = distance[i][j] = (char) current++;
			}
		}
		int[] exactValue = new int[current];
		Arrays.fill(exactValue, -1);
		boolean[][] cantBeOne = new boolean[current][current];
		boolean[][] cantBeZero = new boolean[current][current];
		int[][] triangle = new int[1000][3];
		int triangleCount = 0;
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				for (int k = j + 1; k < size; k++) {
					if (k == i || k == j || i == j)
						continue;
					int d1 = distance[i][j];
					int d2 = distance[j][k];
					int d3 = distance[i][k];
					int sum = 0;
					int unknown = 0;
					int first = -1;
					int second = -1;
					if (d1 >= current)
						sum += d1 - '0';
					else {
						unknown++;
						first = d1;
					}
					if (d2 >= current)
						sum += d2 - '0';
					else {
						if (first == -1)
							first = d2;
						else
							second = d2;
						unknown++;
					}
					if (d3 >= current)
						sum += d3 - '0';
					else {
						unknown++;
						if (first == -1)
							first = d3;
						else
							second = d3;
					}
					if (unknown == 0) {
						if (sum == 1)
							return false;
					} else if (unknown == 1) {
						if (sum <= 1) {
							if (exactValue[first] == 1 - sum)
								return false;
							exactValue[first] = sum;
						}
					} else if (unknown == 2) {
						if (sum == 1)
							cantBeZero[first][second] = true;
						else
							cantBeOne[first][second] = true;
					} else {
						triangle[triangleCount][0] = d1;
						triangle[triangleCount][1] = d2;
						triangle[triangleCount++][2] = d3;
					}
				}
			}
		}
		int mask = 0;
		for (int i = 0; i < current; i++) {
			if (exactValue[i] == -1) {
				mask += 1 << i;
			}
		}
		for (int i = mask; ; i = (i - 1) & mask) {
			for (int j = 0; j < current; j++) {
				if ((mask >> j & 1) != 0)
					exactValue[j] = i >> j & 1;
			}
			boolean good = true;
			for (int j = 0; j < current && good; j++) {
				for (int k = 0; k < current && good; k++) {
					if (cantBeOne[j][k] && exactValue[j] + exactValue[k] == 1 || cantBeZero[j][k] && exactValue[j] + exactValue[k] == 0)
						good = false;
				}
			}
			for (int j = 0; j < triangleCount && good; j++) {
				if (exactValue[triangle[j][0]] + exactValue[triangle[j][1]] + exactValue[triangle[j][2]] == 1)
					good = false;
			}
			if (good) {
				for (int j = 0; j < size; j++) {
					for (int k = 0; k < size; k++) {
						if (distance[j][k] < current)
							distance[j][k] = (char) ('0' + exactValue[distance[j][k]]);
					}
				}
				return true;
			}
			if (i == 0)
				return false;
		}
	}
}
