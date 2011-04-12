package Timus.Part7;

import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class Task1617 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[] diameters = in.readIntArray(n);
		Arrays.sort(diameters);
		int result = 0;
		for (int i = 0; i < n - 3; i++) {
			if (diameters[i] == diameters[i + 3]) {
				result++;
				i += 3;
			}
		}
		out.println(result);
	}
}

