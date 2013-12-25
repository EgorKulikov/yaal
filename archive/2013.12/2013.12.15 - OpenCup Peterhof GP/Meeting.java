package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Meeting {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
    	int n = in.readInt();
		double[][] a = new double[2 * n - 1][2 * n - 1];
		for (double[] ints : a) {
			Arrays.fill(ints, -1);
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n + i; j++) {
				a[i][j] = in.readInt();
			}
		}
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < 2 * n - 2 - i; j++) {
				a[n + i][1 + i + j] = in.readInt();
			}
		}
		double[][][] z = new double[6][][];
		for (int i = 0; i < 6; i++) {
			z[i] = calc(n, a);
			a = rotate(a);
			for (int j = 0; j < 6 - i; j++) {
				z[i] = rotate(z[i]);
			}
		}
		double min = 1e100;
		int resi = 0;
		int resj = 0;
		for (int i = 0; i < 2 * n - 1; i++) {
			for (int j = 0; j < 2 * n - 1; j++) {
				if (a[i][j] >= 0) {
					double s = 0;
					for (int k = 0; k < 6; k++) {
						s += z[k][i][j];
					}
					if (s < min) {
						min = s;
						resi = i;
						resj = j;
					}
				}
			}
		}
		if (resi >= n) {
			resj -= (resi - n) + 1;
		}
		resi++;
		resj++;
		out.printLine(resi, resj);
	}

	private double[][] rotate(double[][] a) {
		int n = (a.length + 1) / 2;
		double[][] res = new double[2 * n - 1][2 * n - 1];
		for (int i = 0; i < 2 * n - 1; i++) {
			for (int j = 0; j < 2 * n - 1; j++) {
				int ii = j;
				int jj = n - 1 - i + j;
				if (ii < 0 || jj < 0 || ii >= 2 * n - 1 || jj >= 2 * n - 1) {
					res[i][j] = -1;
				} else {
					res[i][j] = a[ii][jj];
				}
			}
		}
		return res;
	}

	private double[][] calc(int n, double[][] a) {
		double[][] nn = new double[2 * n - 1][2 * n - 1];
		double[][] ss = new double[2 * n - 1][2 * n - 1];
		for (int i = 0; i < 2 * n - 1; i++) {
			for (int j = 0; j < 2 * n - 1; j++) {
				if (a[i][j] >= 0) {
					if (i == 0 && j == 0) {
						nn[i][j] = 1;
						ss[i][j] = 0;
					} else {
						int dd = Math.max(i, j);
						if (Math.max(i - 1, j) == dd - 1 && a[i - 1][j] >= 0) {
							nn[i][j] += nn[i - 1][j];
							ss[i][j] += ss[i - 1][j] + Math.abs(a[i][j] - a[i - 1][j]) * nn[i - 1][j];
						}
						if (Math.max(i, j - 1) == dd - 1 && a[i][j - 1] >= 0) {
							nn[i][j] += nn[i][j - 1];
							ss[i][j] += ss[i][j - 1] + Math.abs(a[i][j] - a[i][j - 1]) * nn[i][j - 1];
						}
						if (i > 0 && j > 0 && Math.max(i - 1, j - 1) == dd - 1 && a[i - 1][j - 1] >= 0) {
							nn[i][j] += nn[i - 1][j - 1];
							ss[i][j] += ss[i - 1][j - 1] + Math.abs(a[i][j] - a[i - 1][j - 1]) * nn[i - 1][j - 1];
						}
					}
				}
			}
		}
		for (int i = 0; i < 2 * n - 1; i++) {
			for (int j = 0;  j < 2 * n - 1; j++) {
				if (nn[i][j] > 0) {
					ss[i][j] /= nn[i][j];
				}
			}
		}
		return ss;
	}

}
