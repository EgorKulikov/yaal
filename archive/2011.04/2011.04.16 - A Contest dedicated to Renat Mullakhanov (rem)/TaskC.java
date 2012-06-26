package April2011.UVaAContestDedicatedToRenatMullakhanov;

import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskC implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int numberCount = in.readInt();
		int digitCount = in.readInt();
		int[][] digits = new int[digitCount][numberCount];
		in.readIntArrays(digits);
		int[] plain = new int[numberCount];
		for (int i = 0; i < numberCount; i++)
			plain[i] = i;
		long result = 0;
		for (int[] digit : digits) {
			Arrays.sort(digit);
			for (int i = 0; i < numberCount; i++)
				result += Math.abs(plain[i] - digit[i]);
		}
		out.println("Case " + testNumber + ": " + result);
	}
}

