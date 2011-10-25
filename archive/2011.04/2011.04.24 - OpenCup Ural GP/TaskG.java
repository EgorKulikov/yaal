import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskG implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long side = in.readInt();
		long[] heights = new long[4];
		int countZeroes = 0;
		for (int i = 0; i < 4; i++) {
			if ((heights[i] = in.readLong()) == 0)
				countZeroes++;
		}
		if (countZeroes == 4) {
			out.println(0);
			return;
		}
		if (countZeroes == 3) {
			out.println("ambiguous");
			return;
		}
		if (countZeroes == 2) {
			if (heights[0] == 0 && heights[2] == 0 || heights[1] == 0 && heights[3] == 0) {
				out.println("error");
				return;
			}
			out.println("ambiguous");
			return;
		}
		double result = 0;
		if (countZeroes == 1) {
			for (int i = 0; i < 4; i++) {
				if (heights[i] == 0) {
					heights[i] = (heights[(i + 1) & 3] + heights[(i + 3) & 3] - heights[(i + 2) & 3]);
					if (heights[i] > 0) {
						out.println("error");
						return;
					}
					double leftLength = (double)side * Math.abs(heights[i]) / (Math.abs(heights[i]) + heights[(i + 1) & 3]);
					double rightLength = (double)side * Math.abs(heights[i]) / (Math.abs(heights[i]) + heights[(i + 3) & 3]);
					result += leftLength * rightLength * Math.abs(heights[i]) / 6;
				}
			}
		}
		if (heights[0] + heights[2] != heights[1] + heights[3]) {
			out.println("error");
			return;
		}
		int minIndex = 0;
		for (int i = 1; i < 4; i++) {
			if (heights[i] < heights[minIndex])
				minIndex = i;
		}
		result += side * side * heights[minIndex];
		long minHeight = heights[minIndex];
		for (int i = 0; i < 4; i++)
			heights[i] -= minHeight;
		result += side * side * (heights[(minIndex + 1) & 3] + heights[(minIndex + 3) & 3]) / 2.;
		out.printf("%.8f\n", result);
	}
}

