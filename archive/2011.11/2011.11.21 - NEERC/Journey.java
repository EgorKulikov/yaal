package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.Arrays;

public class Journey {
	private BigInteger[][] deltaX, deltaY;
	private int[][] deltaDir;
	private BigInteger[][][] maxShift;
	private int[][] functions;

	private BigInteger[] dx = {BigInteger.ONE, BigInteger.ZERO, BigInteger.ONE.negate(), BigInteger.ZERO};
	private BigInteger[] dy = {BigInteger.ZERO, BigInteger.ONE, BigInteger.ZERO, BigInteger.ONE.negate()};

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		functions = new int[count][];
		for (int i = 0; i < count; i++) {
			int length = in.readInt();
			functions[i] = new int[length];
			for (int j = 0; j < length; j++) {
				String command = in.readString();
				if ("GO".equals(command))
					functions[i][j] = -1;
				else if ("LEFT".equals(command))
					functions[i][j] = -2;
				else if ("RIGHT".equals(command))
					functions[i][j] = -3;
				else
					functions[i][j] = Integer.parseInt(command.substring(1)) - 1;
			}
		}
		boolean[][] reachable = new boolean[count][count];
		for (int i = 0; i < count; i++) {
			for (int j : functions[i]) {
				if (j >= 0)
					reachable[i][j] = true;
			}
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++) {
					if (reachable[j][i] && reachable[i][k])
						reachable[j][k] = true;
				}
			}
		}
		boolean[] cycles = new boolean[count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (reachable[i][j] && reachable[j][j])
					cycles[i] = true;
			}
		}
		deltaX = new BigInteger[count][4];
		deltaY = new BigInteger[count][4];
		deltaDir = new int[count][4];
		for (int[] row : deltaDir)
			Arrays.fill(row, -1);
		maxShift = new BigInteger[count][4][4];
		BigInteger answer = BigInteger.ZERO;
		if (!cycles[0]) {
			go(0, 0);
			for (BigInteger value : maxShift[0][0])
				answer = answer.max(value);
		} else {
			BigInteger x = BigInteger.ZERO;
			BigInteger y = BigInteger.ZERO;
			int dir = 0;
			int index = 0;
			int[] visited = new int[count];
			BigInteger[] enteredX = new BigInteger[count];
			BigInteger[] enteredY = new BigInteger[count];
			while (true) {
				if (visited[index] == 4)
					break;
				if (visited[index] == 0) {
					enteredX[index] = x;
					enteredY[index] = y;
				}
				visited[index]++;
				for (int i : functions[index]) {
					if (i >= 0) {
						if (cycles[i]) {
							index = i;
							break;
						}
						go(i, dir);
						answer = answer.max(x.add(y).add(maxShift[i][dir][0]));
						answer = answer.max(y.subtract(x).add(maxShift[i][dir][1]));
						answer = answer.max(y.negate().subtract(x).add(maxShift[i][dir][2]));
						answer = answer.max(x.subtract(y).add(maxShift[i][dir][3]));
						x = x.add(deltaX[i][dir]);
						y = y.add(deltaY[i][dir]);
						dir = deltaDir[i][dir];
					} else {
						if (i == -1) {
							x = x.add(dx[dir]);
							y = y.add(dy[dir]);
							answer = answer.max(x.abs().add(y.abs()));
						} else if (i == -2) {
							dir = (dir + 1) & 3;
						} else {
							dir = (dir + 3) & 3;
						}
					}
				}
			}
			if (!enteredX[index].equals(x) || !enteredY[index].equals(y)) {
				out.printLine("Infinity");
				return;
			}
		}
		out.printLine(answer);
	}

	private void go(int index, int dir) {
		if (deltaDir[index][dir] != -1)
			return;
		deltaX[index][dir] = BigInteger.ZERO;
		deltaY[index][dir] = BigInteger.ZERO;
		Arrays.fill(maxShift[index][dir], BigInteger.ZERO);
		deltaDir[index][dir] = dir;
		for (int i : functions[index]) {
			if (i >= 0) {
				go(i, deltaDir[index][dir]);
				maxShift[index][dir][0] = maxShift[index][dir][0].max(deltaX[index][dir].add(deltaY[index][dir]).add(maxShift[i][deltaDir[index][dir]][0]));
				maxShift[index][dir][1] = maxShift[index][dir][1].max(deltaX[index][dir].negate().add(deltaY[index][dir]).add(maxShift[i][deltaDir[index][dir]][1]));
				maxShift[index][dir][2] = maxShift[index][dir][2].max(deltaX[index][dir].negate().subtract(deltaY[index][dir]).add(maxShift[i][deltaDir[index][dir]][2]));
				maxShift[index][dir][3] = maxShift[index][dir][3].max(deltaX[index][dir].subtract(deltaY[index][dir]).add(maxShift[i][deltaDir[index][dir]][3]));
				deltaX[index][dir] = deltaX[index][dir].add(deltaX[i][deltaDir[index][dir]]);
				deltaY[index][dir] = deltaY[index][dir].add(deltaY[i][deltaDir[index][dir]]);
				deltaDir[index][dir] = deltaDir[i][deltaDir[index][dir]];
			} else {
				if (i == -1) {
					deltaX[index][dir] = deltaX[index][dir].add(dx[deltaDir[index][dir]]);
					deltaY[index][dir] = deltaY[index][dir].add(dy[deltaDir[index][dir]]);
					maxShift[index][dir][0] = maxShift[index][dir][0].max(deltaX[index][dir].add(deltaY[index][dir]));
					maxShift[index][dir][1] = maxShift[index][dir][1].max(deltaX[index][dir].negate().add(deltaY[index][dir]));
					maxShift[index][dir][2] = maxShift[index][dir][2].max(deltaX[index][dir].negate().subtract(deltaY[index][dir]));
					maxShift[index][dir][3] = maxShift[index][dir][3].max(deltaX[index][dir].subtract(deltaY[index][dir]));
				} else if (i == -2) {
					deltaDir[index][dir] = (deltaDir[index][dir] + 1) & 3;
				} else {
					deltaDir[index][dir] = (deltaDir[index][dir] + 3) & 3;
				}
			}
		}
	}
}
