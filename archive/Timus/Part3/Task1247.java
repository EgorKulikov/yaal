package Timus.Part3;

import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Task1247 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int length = in.readInt();
		int n = in.readInt();
		int[] sequence = in.readIntArray(length);
		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum += sequence[i];
			if (sum < i + 1 || sum > n + i + 1) {
				out.println("NO");
				return;
			}
		}
		out.println("YES");
	}
}

