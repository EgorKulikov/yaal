package on2013_02.on2013_02_03_OpenCup_Onsite.TaskI;



import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskI {
	private static final int MOD = 1000000009;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int w = in.readInt();
		long h = in.readLong();
		int n = in.readInt();
		int[] x = new int[n];
		long[] y = new long[n];
		for (int i = 0; i < n; i++) {
			x[i] = in.readInt();
			y[i] = in.readLong();
		}
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (y[j] < y[i]) {
					int t = x[i];
					x[i] = x[j];
					x[j] = t;
					long tt = y[i];
					y[i] = y[j];
					y[j] = tt;
				}
			}
		}
		long yy = 1;
		long[] p = new long[w];
		p[0] = 1;
		int k = 0;
		while (k < n && y[k] == 1) k++;
		long[][] a = new long[w][w];
		for (int i = 0; i < w; i++) {
			for (int j = i - 1; j <= i + 1; j++) {
				if (j >= 0 && j < w) {
					a[i][j] = 1;
				}
			}
		}
		long[][][] results = new long[60][][];
		results[0] = a;
		long[] current = Matrix.convert(a);
		long[] next = new long[w * w];
		for (int i = 1; i < 60; i++) {
			multiply(next, current, current, MOD, w);
			results[i] = Matrix.convert(next, w);
			long[] temp = current;
			current = next;
			next = temp;
		}
		while (yy < h) {
			if (k < n && y[k] == yy + 1) {
				boolean[] z = new boolean[w];
				while (k < n && y[k] == yy + 1) {
					z[x[k] - 1] = true;
					k++;
				}
				long[] pp = new long[w];
				for (int i = 0; i < w; i++) {
					for (int j = i - 1; j <= i + 1; j++) {
						if (j >= 0 && j < w && !z[j]) {
							pp[j] += p[i];
							pp[j] %= MOD;
						}
					}
				}
				p = pp;
				yy++;
			} else {
				long yyy;
				if (k == n) {
					yyy = h;
				} else {
					yyy = y[k] - 1;
				}
				long delta = yyy - yy;
				long[] pp = new long[w];
				for (int l = 0; l < 60; l++) {
					if ((delta >> l & 1) == 0)
						continue;
					Arrays.fill(pp, 0);
					for (int i = 0; i < w; i++) {
						for (int j = 0; j < w; j++) {
							pp[j] += p[i] * results[l][i][j] % MOD;
							pp[j] %= MOD;
						}
					}
					long[] temp = p;
					p = pp;
					pp = temp;
				}
				yy = yyy;
			}
		}
		out.printLine(p[w - 1]);

    }

	private static void power(long[] matrix, long[] result, long[] temp, long exponent, long mod, int side) {
		if (exponent == 0) {
			for (int i = 0; i < matrix.length; i += side + 1)
				result[i] = 1 % mod;
			return;
		}
		if ((exponent & 1) == 0) {
			power(matrix, temp, result, exponent >> 1, mod, side);
			multiply(result, temp, temp, mod, side);
		} else {
			power(matrix, temp, result, exponent - 1, mod, side);
			multiply(result, temp, matrix, mod, side);
		}
	}

	private static void multiply(long[] c, long[] a, long[] b, long mod, int side) {
		Arrays.fill(c, 0);
		for (int i = 0; i < side; i++) {
			for (int jj = 0; jj < side; jj += 8) {
				int end = Math.min(jj + 8, side);
				for (int j = jj; j < end; j++) {
					for (int k = 0; k < side; k++)
						c[i * side + k] += a[i * side + j] * b[j * side + k];
				}
				for (int k = 0; k < side; k++)
					c[i * side + k] %= MOD;
			}
		}
		for (int i = 0; i < c.length; i++)
			c[i] %= mod;
	}

	public static long[] power(long[] matrix, long exponent, long mod, int side) {
		long[] result = new long[matrix.length];
		long[] temp = new long[result.length];
		power(matrix, result, temp, exponent, mod, side);
		return result;
	}



	private long[][] pow(long[][] a, long b) {
		long[] matrix = Matrix.convert(a);
		matrix = power(matrix, b, MOD, a.length);
		return Matrix.convert(matrix, a.length);
/*		int w = a.length;
		int[][] res = new int[w][w];
		for (int i = 0; i < w; i++) {
			res[i][i] = 1;
		}
		while (b > 0) {
			if (b % 2 == 1) {
				res = mult(res, a);
				b--;
			}
			a = mult(a, a);
			b = b / 2;
		}
		return res;*/
	}

	private int[][] mult(int[][] a, int[][] b) {
		int w = a.length;
		int[][] res = new int[w][w];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < w; j++) {
				for (int k = 0; k < w; k++) {
					res[i][j] += (1L * a[i][k] * b[k][j]) % MOD;
					res[i][j] %= MOD;
				}
			}
		}
		return res;
	}
}
