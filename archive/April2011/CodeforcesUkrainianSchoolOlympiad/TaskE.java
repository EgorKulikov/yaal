package April2011.CodeforcesUkrainianSchoolOlympiad;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskE implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int pointCount = in.readInt();
		int[] x = new int[pointCount];
		int[] y = new int[pointCount];
		in.readIntArrays(x, y);
		long sumSquares = 0;
		long sumX = 0;
		long sumY = 0;
		for (int i = 0; i < pointCount; i++) {
			sumSquares += x[i] * x[i] + y[i] * y[i];
			sumX += x[i];
			sumY += y[i];
		}
		out.println(sumSquares * pointCount - sumX * sumX - sumY * sumY);
	}
}

