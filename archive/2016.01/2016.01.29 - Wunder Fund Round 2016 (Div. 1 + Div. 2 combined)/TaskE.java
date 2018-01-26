package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskE {
	static int BUBEN = 512;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		double[] bx = new double[(n - 1) / BUBEN + 2];
		double[] by = new double[bx.length];
		int[] bAng = new int[bx.length];
		int[] len = new int[n];
		int[] ang = new int[n];
		for (int i = 0; i < n; i++) {
			len[i] = 1;
		}
		for (int i = 0; i < bx.length - 1; i++) {
			bx[i] = i * BUBEN;
		}
		double[] cosa = new double[360];
		double[] sina = new double[360];
		for (int i = 0; i < 360; i++) {
			double a = Math.PI * i / 180;
			cosa[i] = Math.cos(a);
			sina[i] = Math.sin(a);
		}
		bx[bx.length - 1] = n;
		for (int i = 0; i < m; i++) {
			int type = in.readInt();
			int at = in.readInt() - 1;
			int delta = in.readInt();
			int chunk = at / BUBEN;
			if (type == 1) {
				int cAng = bAng[chunk];
				for (int j = chunk * BUBEN; j <= at; j++) {
					cAng += ang[j];
					if (cAng >= 360) {
						cAng -= 360;
					}
				}
				len[at] += delta;
				double dx = delta * cosa[cAng];
				double dy = -delta * sina[cAng];
				for (int j = chunk + 1; j < bx.length; j++) {
					bx[j] += dx;
					by[j] += dy;
				}
			} else {
				int cAng = bAng[chunk];
				double cx = bx[chunk];
				double cy = by[chunk];
				for (int j = chunk * BUBEN; j < at; j++) {
					cAng += ang[j];
					if (cAng >= 360) {
						cAng -= 360;
					}
					cx += len[j] * cosa[cAng];
					cy -= len[j] * sina[cAng];
				}
				ang[at] += delta;
				if (ang[at] >= 360) {
					ang[at] -= 360;
				}
				for (int j = chunk + 1; j < bx.length; j++) {
					bAng[j] += delta;
					if (bAng[j] >= 360) {
						bAng[j] -= 360;
					}
					double dx = bx[j] - cx;
					double dy = by[j] - cy;
					bx[j] = cx + dx * cosa[delta] + dy * sina[delta];
					by[j] = cy + dy * cosa[delta] - dx * sina[delta];
				}
			}
			out.printLine(bx[bx.length - 1], by[by.length - 1]);
		}
	}
}
