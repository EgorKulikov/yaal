package April2011.CodeforcesBetaRound66;

import net.egork.utils.old.io.old.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int[] dimensions = in.readIntArray(3);
		int slices = in.readInt();
		Arrays.sort(dimensions);
		long result = 1;
		int firstSlices = Math.min(dimensions[0] - 1, slices / 3);
		result *= firstSlices + 1;
		slices -= firstSlices;
		int secondSlices = Math.min(dimensions[1] - 1, slices / 2);
		result *= secondSlices + 1;
		slices -= secondSlices;
		result *= Math.min(dimensions[2], slices + 1);
		out.println(result);
	}
}

