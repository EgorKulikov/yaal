import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[] a = new int[n];
		int s = 0;
		for (int i = 0; i < n; i++) {
			a[i] = in.readInt() * (n + 1);
			s += a[i];
		}
		s /= n + 1;
		int over = 0;
		for (int i = 0; i < n; i++) {
			over += Math.max(a[i] - s, 0);
		}
		for (int i = 0; i < n; i++) {
			out.print(Math.max(a[i] - s, 0) * 100 / over + " ");
		}
	}
}

