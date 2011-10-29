import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		String s = in.readLine();
		if (s.contains("<") || s.contains(">") || s.contains("'") || s.contains("\"")) {
			out.println(0);
			return;
		}
		if (!s.contains("&")) {
			out.println(-1);
			return;
		}
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '&') {
				int j = i + 1;
				int count = 0;
				while (s.substring(j).startsWith("amp;")) {
					count++;
					j += 4;
				}
				if (s.substring(j).startsWith("lt;") || s.substring(j).startsWith("gt;") ||
					s.substring(j).startsWith("apos;") || s.substring(j).startsWith("quot;"))
				{
					count++;
				}
				answer = Math.min(answer, count);
			}
		}
		out.println(answer);
	}
}

