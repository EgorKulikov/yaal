package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.Random;

public class SerejaAndGraph {
	Random random = new Random(239);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] p = new int[10];
		for (int i = 0; i < p.length; i++) {
			p[i] = nextProbablePrime((int) (random.nextInt((int) 1e9) + 1e9));
		}
		long[][][] matrix = new long[p.length][count][count];
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			for (int j = 0; j < p.length; j++) {
				int value = random.nextInt(p[j] - 1) + 1;
				matrix[j][from][to] = value;
				matrix[j][to][from] = p[j] - value;
			}
		}
		if (count % 2 == 1) {
			out.printLine("NO");
			return;
		}
		for (int i = 0; i < p.length; i++) {
			boolean good = true;
			for (int j = 0; j < count; j++) {
				long max = 0;
				int r = -1;
				int c = -1;
				for (int k = j; k < count; k++) {
					for (int l = j; l < count; l++) {
						if (matrix[i][k][l] > max) {
							max = matrix[i][k][l];
							r = k;
							c = l;
						}
					}
				}
				if (max == 0) {
					good = false;
					break;
				}
				for (int k = j; k < count; k++) {
					long t = matrix[i][j][k];
					matrix[i][j][k] = matrix[i][r][k];
					matrix[i][r][k] = t;
				}
				for (int k = j; k < count; k++) {
					long t = matrix[i][k][j];
					matrix[i][k][j] = matrix[i][k][c];
					matrix[i][k][c] = t;
				}
				long reverse = BigInteger.valueOf(max).modInverse(BigInteger.valueOf(p[i])).longValue();
				for (int k = j + 1; k < count; k++) {
					for (int l = count - 1; l >= j; l--) {
						matrix[i][k][l] -= matrix[i][k][j] * matrix[i][j][l] % p[i] * reverse % p[i];
						if (matrix[i][k][l] < 0)
							matrix[i][k][l] += p[i];
					}
				}
			}
			if (good) {
				out.printLine("YES");
				return;
			}
		}
		out.printLine("NO");
    }

	private int nextProbablePrime(int from) {
		while (!BigInteger.valueOf(from).isProbablePrime(20))
			from++;
		return from;
	}
}
