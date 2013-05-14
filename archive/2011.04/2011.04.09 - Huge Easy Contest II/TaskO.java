package April2011.UVaHugeEasyContestII;

import net.egork.utils.old.io.old.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskO implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		if (testNumber != 1)
			out.println();
		out.println("Case " + testNumber + ":");
		for (int it = Integer.parseInt(in.readLine()); it > 0; it--) {
			String line = in.readLine(false);
			boolean lastIsSpace = false;
			for (int i = 0; i < line.length(); i++) {
				char ch = line.charAt(i);
				if (ch == ' ') {
					if (!lastIsSpace) {
						lastIsSpace = true;
						out.print(' ');
					}
				} else {
					lastIsSpace = false;
					out.print(ch);
				}
			}
			out.println();
		}
	}
}

