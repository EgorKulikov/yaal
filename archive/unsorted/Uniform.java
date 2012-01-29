package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Uniform {
	boolean[] mark;
	double[][] bound;
	int n;
	private static final int MAGIC = 100;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		n = in.readInt();
		int m = in.readInt();
		double[][] matrix = new double[n - 2][n - 2];
		double[] rightPart = new double[n - 2];
		int[] cntAdjZero = new int[n];
		bound = new double[n][n];
		for (int i = 0; i < n; ++i) Arrays.fill(bound[i], 1e100);
		for (int i = 0; i < m; ++i) {
			int a = in.readInt() - 1;
			int b = in.readInt() - 1;
			int c = in.readInt();
			bound[a][b] = Math.min(bound[a][b], c);
			bound[b][a] = Math.min(bound[b][a], c);
			if (a > 0 && a < n - 1) {
				matrix[a - 1][a - 1] += 1.0;
				if (b > 0 && b < n - 1)
					matrix[a - 1][b - 1] -= 1.0;
				if (b == n - 1)
					rightPart[a - 1] += 1.0;
			}
			if (b > 0 && b < n - 1) {
				matrix[b - 1][b - 1] += 1.0;
				if (a > 0 && a < n - 1)
					matrix[b - 1][a - 1] -= 1.0;
				if (a == n - 1)
					rightPart[b - 1] += 1.0;
			}
			if (a == 0 && b != 0) ++cntAdjZero[b];
			if (b == 0 && a != 0) ++cntAdjZero[a];
		}
		mark = new boolean[n];
		dfs(0);
		if (!mark[n - 1]) {
			out.printLine(0.0);
			return;
		}
		int numActive = 0;
		for (int i = 1; i < n - 1; ++i)
			if (mark[i])
				++numActive;
		int[] active = new int[numActive];
		numActive = 0;
		for (int i = 1; i < n - 1; ++i)
			if (mark[i])
				active[numActive++] = i;
		double[][] activeMatrix = new double[numActive][numActive];
		double[] activeRightPart = new double[numActive];
		for (int i = 0; i < numActive; ++i) {
			for (int j = 0; j < numActive; ++j)
				activeMatrix[i][j] = matrix[active[i] - 1][active[j] - 1];
			activeRightPart[i] = rightPart[active[i] - 1];
		}
		double[] activeSolution = solveSystem(numActive, activeMatrix, activeRightPart);
		double maxMul = bound[0][n - 1];
		for (int i = 0; i < numActive; ++i) {
			maxMul = Math.min(maxMul, getBound(bound[0][active[i]], activeSolution[i]));
			maxMul = Math.min(maxMul, getBound(bound[n - 1][active[i]], 1.0 - activeSolution[i]));
			for (int j = 0; j < numActive; ++j) {
				maxMul = Math.min(maxMul, getBound(bound[active[i]][active[j]], activeSolution[i] - activeSolution[j]));
			}
		}
		double res = 0;
		for (int i = 0; i < numActive; ++i)
			res += activeSolution[i] * cntAdjZero[active[i]];
		res += cntAdjZero[n - 1];
		out.printLine(res * maxMul);
	}

	private double getBound(double bound, double cur) {
		if (bound == 0.0 && Math.abs(cur) < 1e-10)
			return 1e100;
		return bound / Math.abs(cur);
	}

	private void dfs(int at) {
		if (mark[at]) return;
		mark[at] = true;
		for (int i = 0; i < n; ++i)
			if (bound[at][i] < 1e100)
				dfs(i);
	}

	private double[] solveSystem(int n, double[][] matrix, double[] rightPart) {
		if (n != matrix.length) throw new RuntimeException();
		if (n != rightPart.length) throw new RuntimeException();
		for (int i = 0; i < n; ++i) {
			double by = matrix[i][i];
			if (by == 0.0) throw new RuntimeException();
			for (int j = 0; j < n; ++j) {
				matrix[i][j] /= by;
			}
			rightPart[i] /= by;
		}
		double[][] powMatrix = new double[n + 1][n + 1];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j)
				if (i != j)
					powMatrix[i][j] = -matrix[i][j];
			powMatrix[i][n] = rightPart[i];
		}
		powMatrix[n][n] = 1.0;
		for (int i = 0; i < MAGIC; ++i) {
			powMatrix = mul(powMatrix, powMatrix);
		}
		double[] res = new double[n];
		for (int i = 0; i < n; ++i)
			res[i] = powMatrix[i][n];
		return res;
	}

	private double[][] mul(double[][] a, double[][] b) {
		int n = a.length;
		double[][] c = new double[n][n];
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j) {
				double s = 0.0;
				for (int k = 0; k < n; ++k) s += a[i][k] * b[k][j];
				c[i][j] = s;
			}
		return c;
	}
}
