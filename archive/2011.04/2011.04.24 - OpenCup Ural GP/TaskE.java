import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskE implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) a[i] = in.readInt() - 1;
		int[] p = new int[n];
		for (int i = 0; i < n; i++) p[a[i]] = i;
		boolean[] s = new boolean[n];
		List<int[]> res = new ArrayList<int[]>();
		while (true) {
			boolean swap = false;
			Arrays.fill(s, false);
			boolean ok = true;
			for (int i = 0; i < n; i++) {
				if ((i == 0 || p[i] > p[i - 1]) && (i == n - 1 || p[i] > p[i + 1])) {
					swap = !swap;
				} else {
					s[i] = swap;
					if (s[i]) ok = false;
				}
			}
			if (ok) break;
			int[] aa = new int[n];
			int m = 0;
			for (int i = 0; i < n; i++) {
				if (!s[a[i]]) aa[m++] = a[i];
			}
			m = n;
			for (int i = 0; i < n; i++) {
				if (s[a[i]]) aa[--m] = a[i];
			}
			a = aa;
			res.add(a);
			for (int i = 0; i < n; i++) p[a[i]] = i;
		}
		out.println(res.size());
		for (int[] ints : res) {
			for (int i : ints) {
				out.print((i + 1) + " ");
			}
			out.println();
		}
	}
}

