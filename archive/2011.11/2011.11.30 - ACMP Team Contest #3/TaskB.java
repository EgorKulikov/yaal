package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int[][] matrix = new int[size - 1][size - 1];
		boolean[][] connected = new boolean[size][size];
		for (int i = 0; i < size; i++)
			connected[i][i] = true;
		int edgeCount = in.readInt();
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			if (from > to) {
				int temp = from;
				from = to;
				to = temp;
			}
			connected[from][to] = connected[to][from] = true;
			matrix[from][from]++;
			if (to != size - 1) {
				matrix[to][to]++;
				matrix[from][to]--;
				matrix[to][from]--;
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++)
					connected[j][k] |= connected[j][i] && connected[i][k];
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (!connected[i][j]) {
					out.printLine(0);
					return;
				}
			}
		}
		size--;
		BigInteger[] value = new BigInteger[1 << size];
		value[0] = BigInteger.ONE;
		for (int i = 1; i < value.length; i++) {
			int row = size - Integer.bitCount(i);
			int c = 1;
			value[i] = BigInteger.ZERO;
			for (int j = 0; j < size; j++) {
				if ((i >> j & 1) == 0)
					continue;
				if (c == 1)
					value[i] = value[i].add(value[i - (1 << j)].multiply(BigInteger.valueOf(matrix[row][j])));
				else
					value[i] = value[i].subtract(value[i - (1 << j)].multiply(BigInteger.valueOf(matrix[row][j])));
				c = -c;
			}
		}
		out.printLine(value[value.length - 1]);
	}
}
