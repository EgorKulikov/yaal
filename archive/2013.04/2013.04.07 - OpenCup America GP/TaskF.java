package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		if (n == 0) throw new UnknownError();
		double res = 0;
		for (int pi = 0; pi < n; ++pi) {
			int f = in.readInt();
			double[][][] faces = new double[f][][];
			for (int fi = 0; fi < f; ++fi) {
				int v = in.readInt();
				double[][] face = new double[v][3];
				faces[fi] = face;
				for (int i = 0; i < v; ++i)
					for (int j = 0; j < 3; ++j)
						face[i][j] = in.readDouble();
			}
			int cnt = 0;
			double[] s = new double[3];
			for (double[][] face : faces) for (double[] vertex : face) {
				++cnt;
				for (int i = 0; i < 3; ++i) s[i] += vertex[i];
			}
			for (int i = 0; i < 3; ++i) s[i] /= cnt;
			for (double[][] face : faces) {
				double dist = 0;
				{
					double[] v1 = new double[3];
					double[] v2 = new double[3];
					for (int i = 0; i < 3; ++i) {
						v1[i] = face[1][i] - face[0][i];
						v2[i] = face[2][i] - face[0][i];
					}
					double[] pr = new double[3];
					for (int i = 0; i < 3; ++i) {
						int j = (i + 1) % 3;
						int k = (i + 2) % 3;
						pr[i] = (v1[j] * v2[k] - v1[k] * v2[j]);
					}
					double z = 0;
					for (int i = 0; i < 3; ++i) z += pr[i] * pr[i];
					z = Math.sqrt(z);
					for (int i = 0; i < 3; ++i) pr[i] /= z;
					for (int i = 0; i < 3; ++i) dist += (s[i] - face[0][i]) * pr[i];
					dist = Math.abs(dist);
				}

				double area = 0;
				for (int fi = 0; fi < face.length; ++fi) {
					int fj = (fi + 1) % face.length;
					double[] v1 = new double[3];
					double[] v2 = new double[3];
					for (int i = 0; i < 3; ++i) {
						v1[i] = face[fi][i] - face[0][i];
						v2[i] = face[fj][i] - face[0][i];
					}
					double[] pr = new double[3];
					for (int i = 0; i < 3; ++i) {
						int j = (i + 1) % 3;
						int k = (i + 2) % 3;
						pr[i] = (v1[j] * v2[k] - v1[k] * v2[j]);
					}
					double z = 0;
					for (int i = 0; i < 3; ++i) z += pr[i] * pr[i];
					z = Math.sqrt(z);
					area += z;
				}
				res += dist * area / 6.0;
			}
		}
		out.printLine(String.format("%.10f", res));
    }
}
