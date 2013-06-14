import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int a = in.readInt();
		int b = Math.min(in.readInt(), 999999999);
		int mod = in.readInt();
		if (b >= mod) {
			out.println(2);
			return;
		}
		int delta = 1000000000 % mod;
		int current = 0;
		int x = Math.min(a + 1, mod);
		int max = mod - b;
		for (int i = 0; i < x; i++) {
			if (current > 0 && current < max) {
				out.printf("1 %09d\n", i);
				return;
			}
			current += delta;
			if (current >= mod)
				current -= mod;
		}
		out.println(2);
	}
}

