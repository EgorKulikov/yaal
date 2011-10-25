import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		char[] s = in.readString().toCharArray();
		for (int i = 0; i < s.length; i++) {
			if (s[i] == '(') {
				//noinspection StatementWithEmptyBody
				for (i++; s[i] != ')'; i++);
				for (int j = i - 1; s[j] != '('; j--)
					out.print(s[j]);
			} else
				out.print(s[i]);
		}
		out.println();
	}
}

