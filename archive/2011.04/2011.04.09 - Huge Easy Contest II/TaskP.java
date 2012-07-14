package April2011.UVaHugeEasyContestII;

import net.egork.utils.old.io.old.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskP implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int starCount = in.readInt();
		double maxDistance = in.readDouble();
		double[] x = new double[starCount];
		double[] y = new double[starCount];
		in.readDoubleArrays(x, y);
		boolean[][] sameConstellation = new boolean[starCount][starCount];
		for (int i = 0; i < starCount; i++) {
			for (int j = i + 1; j < starCount; j++) {
				double dx = x[i] - x[j];
				double dy = y[i] - y[j];
				sameConstellation[i][j] = sameConstellation[j][i] = maxDistance * maxDistance + 1e-8 > dx * dx + dy * dy;
			}
		}
		boolean[] accounted = new boolean[starCount];
		int result = 0;
		int[] queue = new int[starCount];
		for (int i = 0; i < starCount; i++) {
			if (accounted[i])
				continue;
			result++;
			queue[0] = i;
			int size = 1;
			accounted[i] = true;
			for (int j = 0; j < size; j++) {
				int current = queue[j];
				for (int k = 0; k < starCount; k++) {
					if (!accounted[k] && sameConstellation[current][k]) {
						accounted[k] = true;
						queue[size++] = k;
					}
				}
			}
		}
		out.println("Case " + testNumber + ": " + result);
	}
}

