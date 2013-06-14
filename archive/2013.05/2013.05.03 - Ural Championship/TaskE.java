package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		double[][] vec = new double[n][3];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < 3; ++j) vec[i][j] = in.readDouble();
			norm(vec[i]);
		}
		double[][] rot = rotateToZero(vec[0]);
		for (int i = 0; i < n; ++i) {
			vec[i] = mul(rot, vec[i]);
		}
		int mx = 1;
		for (int i = 2; i < n; ++i) {
			if (Math.abs(vec[i][2]) < Math.abs(vec[mx][2]))
				mx = i;
		}
		Set<Long> seen = new HashSet<Long>();
		double unit = 0.5e-6;
		for (int i = 0; i < n; ++i) {
			int[] d = new int[3];
			for (d[0] = 0; d[0] < 2; ++d[0])
				for (d[1] = 0; d[1] < 2; ++d[1])
					for (d[2] = 0; d[2] < 2; ++d[2]) {
						long cur = 0;
						for (int coord = 0; coord < 3; ++coord) {
							long a = (long) (Math.floor(vec[i][coord] / unit - 0.5)) + d[coord];
							cur = cur * 3137317 + a;
						}
						seen.add(cur);
					}
		}
		int res = 0;
		for (int other = 0; other < n; ++other) {
			double[][] nvec = new double[n][];
			double[][] nnvec = new double[n][3];
			double[][] nrot = rotateToZero(vec[other]);
			for (int i = 0; i < n; ++i) nvec[i] = mul(nrot, vec[i]);
			for (int mxi = 0; mxi < n; ++mxi) if (mxi != other)
				if (Math.abs(nvec[mxi][2] - vec[mx][2]) < 1e-6) {
					double alpha = Math.atan2(vec[mx][1], vec[mx][0]) - Math.atan2(nvec[mxi][1], nvec[mxi][0]);
					for (int i = 0; i < n; ++i) {
						double c0 = nvec[i][0];
						double c1 = nvec[i][1];
						nnvec[i][0] = Math.cos(alpha) * c0 - Math.sin(alpha) * c1;
						nnvec[i][1] = Math.sin(alpha) * c0 + Math.cos(alpha) * c1;
						nnvec[i][2] = nvec[i][2];
					}
					double s = 0;
					for (int j = 0; j < 3; ++j) {
						double d = vec[mx][j] - nnvec[mxi][j];
						s += d * d;
					}
					if (s > 1e-12) continue;
					boolean ok = true;
					for (int i = 0; i < n; ++i) {
						boolean matched = false;
						for (int j = 0; j < n; ++j) {
							s = 0;
							for (int k = 0; k < 3; ++k) {
								double d = vec[i][k] - nnvec[j][k];
								s += d * d;
							}
							if (s < 1e-12) {
								matched = true;
								break;
							}
						}
						if (!matched) {
							ok = false;
							break;
						}
					}
					if (ok) ++res;
				}
		}
		out.printLine(res);
    }

	private void norm(double[] doubles) {
		double z = 0;
		for (double x : doubles) z += x * x;
		z = Math.sqrt(z);
		for (int i = 0; i < doubles.length; ++i) doubles[i] /= z;
	}

	private double[] mul(double[][] rot, double[] coord) {
		double[] res = new double[3];
		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 3; ++j) {
				res[i] += rot[i][j] * coord[j];
			}
		return res;
	}

	private double[][] rotateToZero(double[] coord) {
		double[][] cur = new double[][]{
			new double[]{1, 0, 0},
			new double[]{0, 1, 0},
			new double[]{0, 0, 1}};
		if (coord[0] != 0) {
			double alpha = Math.atan2(coord[0], coord[2]);
			double[][] matr = new double[][]{
				new double[]{Math.cos(alpha), 0, -Math.sin(alpha)},
				new double[]{0, 1, 0},
				new double[]{Math.sin(alpha), 0, Math.cos(alpha)}};
			cur = mul(matr, cur);
			coord = mul(matr, coord);
		}
		if (coord[1] != 0) {
			double alpha = Math.atan2(coord[1], coord[2]);
			double[][] matr = new double[][]{
				new double[]{1, 0, 0},
				new double[]{0, Math.cos(alpha), -Math.sin(alpha)},
				new double[]{0, Math.sin(alpha), Math.cos(alpha)}};
			cur = mul(matr, cur);
			coord = mul(matr, coord);
		}
		if (coord[2] < 0) {
			double alpha = Math.PI;
			double[][] matr = new double[][]{
				new double[]{1, 0, 0},
				new double[]{0, Math.cos(alpha), -Math.sin(alpha)},
				new double[]{0, Math.sin(alpha), Math.cos(alpha)}};
			cur = mul(matr, cur);
			coord = mul(matr, coord);
		}
		return cur;
	}

	private double[][] mul(double[][] a, double[][] b) {
		double[][] c = new double[3][3];
		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 3; ++j)
				for (int k = 0; k < 3; ++k)
					c[i][j] += a[i][k] * b[k][j];
		return c;
	}
}
