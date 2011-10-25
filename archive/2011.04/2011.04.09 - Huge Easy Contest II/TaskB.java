package April2011.UVaHugeEasyContestII;

import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		String a = in.readString();
		in.readString();
		String b = in.readString();
		in.readString();
		String c = in.readString();
		boolean isAllOnes = true;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != '1')
				isAllOnes = false;
		}
		for (int i = 0; i < b.length(); i++) {
			if (b.charAt(i) != '1')
				isAllOnes = false;
		}
		for (int i = 0; i < c.length(); i++) {
			if (c.charAt(i) != '1')
				isAllOnes = false;
		}
		int result = 0;
		if (isAllOnes && a.length() + b.length() == c.length())
			result = 1;
		else {
			for (int i = 2; i < 20; i++) {
				try {
					if (Long.parseLong(a, i) + Long.parseLong(b, i) == Long.parseLong(c, i)) {
						result = i;
						break;
					}
				} catch (NumberFormatException ignored) {
				}
			}
		}
		out.println(result);
	}
}

