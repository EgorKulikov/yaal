package net.egork;

import net.egork.geometry.GeometryUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int moves = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		double[] axx = new double[4];
		double[] ayy = new double[4];
		double[] bxx = new double[4];
		double[] byy = new double[4];
		double ax = 1;
		double bx = 1;
		double ay = -1;
		double by = 1;
		double answer = 0;
		for (int index = 0; index < moves; index++) {
			axx[index & 3] += ax;
			ayy[index & 3] += ay;
			bxx[index & 3] += bx;
			byy[index & 3] += by;
			double nax = bx;
			double nbx = -ax;
			ax = nax;
			bx = nbx;
			double nay = by;
			double nby = -ay;
			ay = nay;
			by = nby;
			if (index >= moves - 4) {
				for (int i = 0; i < count; i++) {
					for (int j = 0; j < count; j++) {
						for (int k = 0; k < count; k++) {
							for (int l = 0; l < count; l++) {
								double cx = axx[0] * x[i] + ayy[0] * y[i] + axx[1] * x[j] + ayy[1] * y[j] + axx[2] * x[k] + ayy[2] * y[k] + axx[3] * x[l] + ayy[3] * y[l];
								double cy = bxx[0] * x[i] + byy[0] * y[i] + bxx[1] * x[j] + byy[1] * y[j] + bxx[2] * x[k] + byy[2] * y[k] + bxx[3] * x[l] + byy[3] * y[l];
								answer = Math.max(answer, GeometryUtils.fastHypot(cx, cy));
							}
						}
					}
				}
			}
		}
		out.printLine("Case #" + testNumber + ": " + answer);
	}
}
