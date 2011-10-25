import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int k = in.readInt();
		double[][] d1 = new double[k + 1][2];
		d1[0][0] = 1;
		double[][] ds1 = new double[k + 1][2];
		ds1[0][0] = 0;
		for (int i = 0; i < k; i++) {
			for (int p = 0; p < 2; p++) {
				for (int x = (i == k - 1) ? 1 : 0; x < 10; x++) {
					for (int y = (i == k - 1) ? 1 : 0; y < 10; y++) {
						int s = x + y + p;
						if (s > 9) {
							d1[i + 1][1] += d1[i][p] / 100;
							ds1[i + 1][1] += (ds1[i][p] + d1[i][p] * (2 + p + 1)) / 100;
						} else {
							d1[i + 1][0] += (d1[i][p] / 100);
							ds1[i + 1][0] += (ds1[i][p] + d1[i][p] * (2 + p)) / 100;
						}
					}
				}
			}
		}
		ds1[k][1] += d1[k][1] * 1;
		double res1 = (ds1[k][0] + ds1[k][1]) / (d1[k][0] + d1[k][1]);

		int n1 = 0;
		int n2 = 0;
		int n3 = 0;
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				if (x < 2 || y < 2) {
					n3++;
				} else if (x == y) {
					n1++;
				} else if (x > y) {
					n2++;
				}
			}
		}

		double[][] d2 = new double[k + 1][n1 + 1];
		double[][] ds2 = new double[k + 1][n1 + 1];
		d2[0][0] = 1;
		double nn = 0.01;
		for (int i = 0; i < k; i++) {
			for (int k1 = 0; k1 <= n1; k1++) {
				if (d2[i][k1] > 0) {
					d2[i][k1] *= nn;
					ds2[i][k1] *= nn;
					double dd = (i == k - 1) ? (n3 + k1 + n2 * 2 - 19) : (n3 + k1 + n2 * 2);
					d2[i + 1][k1] +=
						d2[i][k1] * dd;
					ds2[i + 1][k1] += ds2[i][k1] * dd;

					if (k1 < n1) {
						dd = (n1 - k1);
						d2[i + 1][k1 + 1] += d2[i][k1] * dd;
						ds2[i + 1][k1 + 1] += (ds2[i][k1] + d2[i][k1]) * dd;
					}
				}
			}
		}

		double res2 = 0;
		double s2 = 0;
		for (int k1 = 0; k1 <= n1; k1++) {
			if (d2[k][k1] > 0) {
				res2 += ds2[k][k1];
				s2 += d2[k][k1];
			}

		}
		res2 /= s2;

		double[][] d3 = new double[k + 1][n2 + 1];
		double[][] ds3 = new double[k + 1][n2 + 1];
		d3[0][0] = 1;
		for (int i = 0; i < k; i++) {
			for (int k2 = 0; k2 <= n2; k2++) {
				if (d3[i][k2] > 0) {
					d3[i][k2] *= nn;
					ds3[i][k2] *= nn;
					double dd = (i == k - 1) ? (n3 + n1 + k2 * 2 - 19) : (n3 + n1 + k2 * 2);
					d3[i + 1][k2] +=
						d3[i][k2] * dd;
					ds3[i + 1][k2] += ds3[i][k2] * dd;

					if (k2 < n2) {
						dd = (n2 - k2) * 2;
						d3[i + 1][k2 + 1] += d3[i][k2] * dd;
						ds3[i + 1][k2 + 1] += (ds3[i][k2] + d3[i][k2]) * dd;
					}
				}
			}
		}

		double res3 = 0;
		double s3 = 0;
		for (int k2 = 0; k2 <= n2; k2++) {
			if (d3[k][k2] > 0) {
				res3 += ds3[k][k2];
				s3 += d3[k][k2];
			}

		}
		res3 /= s3;
//		out.println(res1 + " " + res2);
		out.println(res1 + res2 + res3);

//		int s = 0;
//		int n = 0;
//		for (int x = 1; x < 10; x++) {
//			for (int y = 1; y < 10; y++) {
//				for (int xx = 0; xx < 10; xx++) {
//					for (int yy = 0; yy < 10; yy++) {
//						n++;
////						s += 4;
////						if (xx + yy > 9) s += 2;
////						if (x * 10 + y * 10 + xx + yy > 99) s += 2;
//						if (x > 1 && y > 1) s++;
//						if (xx > 1 && yy > 1) {
//							s++;
//							if (x == xx && y == yy || x == yy && y == xx) s--;
//						}
//					}
//				}
//			}
//		}
//		System.err.println(s * 1.0 / n);

	}
}

