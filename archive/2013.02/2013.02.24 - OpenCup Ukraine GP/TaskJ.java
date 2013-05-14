package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	int[] size;

	double res;
	double PRECISION;
	int checkEvery;
	long timeOver;
	boolean isReallyOver = false;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		timeOver = System.currentTimeMillis() + 800;
		size = new int[3];
		for (int i = 0; i < 3; ++i) {
			size[i] = in.readInt();
		}
		res = 0;
		PRECISION = 5.0;
		while (PRECISION > 0.5e-8) {
			PRECISION /= 10.0;
			for (int i = 0; i < 3; ++i) {
				double[][] verts = new double[4][3];
				for (int j = 0; j < 4; ++j) {
					verts[j][i] = size[i];
					verts[j][(i + 1) % 3] = (j / 2) * size[(i + 1) % 3];
					verts[j][(i + 2) % 3] = (j % 2) * size[(i + 2) % 3];
				}
				double[] middle = new double[3];
				for (int j = 0; j < 3; ++j)
					middle[j] = (verts[0][j] + verts[3][j]) / 2;
				double value = distance(middle);
				updateMax(verts, value);
			}
		}
		out.printLine(res);
    }

	private void updateMax(double[][] verts, double curMax) {
		if (isReallyOver) return;
		--checkEvery;
		if (checkEvery < 0) {
			checkEvery = 1000;
			if (timeOver <= System.currentTimeMillis()) {
				isReallyOver = true;
				return;
			}
		}
		if (curMax > res) {
			res = Math.max(curMax, res);
			//System.err.println(res);
		}
		double maxExtra = 0;
		for (int i = 0; i < 3; ++i) {
			double delta = verts[3][i] - verts[0][i];
			maxExtra += delta * delta;
		}
		maxExtra = Math.sqrt(maxExtra);
		maxExtra /= 2;
		if (curMax + maxExtra < res * (1 + PRECISION))
			return;
		int bestCoord = -1;
		double bestDelta = 0;
		for (int i = 0; i < 3; ++i)
			if (verts[0][i] + 1e-10 < verts[3][i]) {
				double delta = verts[3][i] - verts[0][i];
				if (delta > bestDelta) {
					bestDelta = delta;
					bestCoord = i;
				}
			}
		if (bestCoord < 0) return;
		double[][] nverts1;
		double value1;
		{
			double[][] nverts = new double[4][];
			double middle = (verts[0][bestCoord] + verts[3][bestCoord]) / 2;
			for (int i = 0; i < 4; ++i) {
				nverts[i] = verts[i].clone();
				nverts[i][bestCoord] = Math.min(nverts[i][bestCoord], middle);
			}
			nverts1 = nverts;
			double[] mid = new double[3];
			for (int i = 0; i < 3; ++i) mid[i] = (nverts[0][i] + nverts[3][i]) / 2;
			value1 = distance(mid);
		}
		double[][] nverts2;
		double value2;
		{
			double[][] nverts = new double[4][];
			double middle = (verts[0][bestCoord] + verts[3][bestCoord]) / 2;
			for (int i = 0; i < 4; ++i) {
				nverts[i] = verts[i].clone();
				nverts[i][bestCoord] = Math.max(nverts[i][bestCoord], middle);
			}
			nverts2 = nverts;
			double[] mid = new double[3];
			for (int i = 0; i < 3; ++i) mid[i] = (nverts[0][i] + nverts[3][i]) / 2;
			value2 = distance(mid);
		}
		if (value1 > value2) {
			updateMax(nverts1, value1);
			updateMax(nverts2, value2);
		} else {
			updateMax(nverts2, value2);
			updateMax(nverts1, value1);
		}
	}

	private double distance(double[] point) {
		double res = 1e100;
		for (int a = 0; a < 3; ++a)
			for (int b = a + 1; b < 3; ++b) {
				double[] dx = new double[3];
				double[] dy = new double[3];
				dx[a] = 1;
				dy[b] = 1;
				double[] start = new double[3];
				boolean[] used = new boolean[6];
				res = Math.min(res, walk(start, 2 * (3 - a - b), dx, dy, 0, Math.PI / 2, used, point));
			}
		if (res > 2.99) {
			res = 1e100;
			for (int a = 0; a < 3; ++a)
				for (int b = a + 1; b < 3; ++b) {
					double[] dx = new double[3];
					double[] dy = new double[3];
					dx[a] = 1;
					dy[b] = 1;
					double[] start = new double[3];
					boolean[] used = new boolean[6];
					res = Math.min(res, walk(start, 2 * (3 - a - b), dx, dy, 0, Math.PI / 2, used, point));
				}
		}
		return res;
	}

	private double walk(double[] at, int sideId, double[] dx, double[] dy, double minAlpha, double maxAlpha, boolean[] used, double[] point) {
		if (used[sideId]) return 1e100;
		used[sideId] = true;
		double remx = 0;
		double remy = 0;
		double needx = 0;
		double needy = 0;
		int missingCoord = -1;
		int newDir = 0;
		missingCoord = sideId / 2;
		newDir = 1 - (2 * (sideId % 2));
		int destx = -1;
		int desty = -1;
		boolean onSide = true;
		for (int i = 0; i < 3; ++i) {
			if (dx[i] > 0) {
				remx = (size[i] - at[i]) / dx[i];
				destx = 2 * i + 1;
			}
			if (dx[i] < 0) {
				remx = at[i] / -dx[i];
				destx = 2 * i;
			}
			if (dy[i] > 0) {
				remy = (size[i] - at[i]) / dy[i];
				desty = 2 * i + 1;
			}
			if (dy[i] < 0) {
				remy = at[i] / -dy[i];
				desty = 2 * i;
			}
			if (dx[i] != 0) {
				needx = (point[i] - at[i]) / dx[i];
			} else if (dy[i] != 0) {
				needy = (point[i] - at[i]) / dy[i];
			} else {
				if (Math.abs(point[i] - (sideId % 2) * size[i]) > 1e-10) {
					onSide = false;
				}
			}
		}
		double res = 1e100;
		if (onSide) {
			double alpha = Math.atan2(needy, needx);
			if (alpha > minAlpha - 1e-10 && alpha < maxAlpha + 1e-10) {
				res = Math.sqrt(needx * needx + needy * needy);
			}
		}
		double middleAlpha = Math.atan2(remy, remx);
		middleAlpha = Math.max(minAlpha, Math.min(maxAlpha, middleAlpha));
		if (middleAlpha > minAlpha + 1e-10) {
			double[] ndx = new double[3];
			ndx[missingCoord] = newDir;
			double[] ndy = dy;
			double[] nat = at.clone();
			for (int i = 0; i < 3; ++i) {
				nat[i] += remx * dx[i];
				nat[i] -= remx * ndx[i];
			}
			res = Math.min(res, walk(nat, destx, ndx, ndy, minAlpha, middleAlpha, used, point));
		}
		if (middleAlpha < maxAlpha - 1e-10) {
			double[] ndy = new double[3];
			ndy[missingCoord] = newDir;
			double[] ndx = dx;
			double[] nat = at.clone();
			for (int i = 0; i < 3; ++i) {
				nat[i] += remy * dy[i];
				nat[i] -= remy * ndy[i];
			}
			res = Math.min(res, walk(nat, desty, ndx, ndy, middleAlpha, maxAlpha, used, point));
		}
		used[sideId] = false;
		return res;
	}
}
