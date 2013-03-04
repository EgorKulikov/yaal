package net.egork;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int r = in.readInt();
		int c = in.readInt();
		int[][] a = new int[r][c];
		for (int i = 0; i < r; ++i) {
			String s = in.next();
			for (int j = 0; j < c; ++j) {
				a[i][j] = s.charAt(j) - '0';
			}
		}
		int[][] asum = new int[r][];
		for (int i = 0; i < r; ++i) asum[i] = buildSums(a[i]);
		int q = in.readInt();
		long res = 0;
		long prev = 0;
		int pr0 = -1;
		int pc0 = -1;
		int pradius = -1;
		for (int i = 0; i < q; ++i) {
			int r0 = in.readInt();
			int c0 = in.readInt();
			int radius = in.readInt();
			if (r0 == pr0 && c0 == pc0 && radius == pradius) {
				res += prev;
				continue;
			}
			prev = 0;
			pr0 = r0;
			pc0 = c0;
			pradius = radius;
			for (int delta = 0; delta <= radius; ++delta) {
				int mx = (int) Math.floor(Math.sqrt(radius * radius - delta * delta));
				prev += asum[r0 + delta - 1][c0 + mx] - asum[r0 + delta - 1][c0 - mx - 1];
				if (delta > 0)
					prev += asum[r0 - delta - 1][c0 + mx] - asum[r0 - delta - 1][c0 - mx - 1];
			}
			res += prev;
		}
		out.printLine(res);
    }

	private int[] buildSums(int[] a) {
		int[] res = new int[a.length + 1];
		for (int i = 0; i < a.length; ++i) {
			res[i + 1] = res[i] + a[i];
		}
		return res;
	}
}
