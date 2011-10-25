import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		char[] s = in.readLine().toCharArray();
		boolean comma = false;
		boolean whitespace = false;
		boolean ignoreWhitespace = false;
		for (int i = 0, sLength = s.length; i < sLength; i++) {
			char c = s[i];
			if (c == ',') {
				if (comma)
					out.print(' ');
				out.print(",");
				ignoreWhitespace = false;
				whitespace = false;
				comma = true;
			} else if (c == '.') {
				if (i != 0)
					out.print(' ');
				i += 2;
				out.print("...");
				ignoreWhitespace = true;
				whitespace = false;
				comma = false;
			} else if (c == ' ') {
				if (!ignoreWhitespace)
					whitespace = true;
			} else {
				if (whitespace || comma)
					out.print(' ');
				whitespace = false;
				ignoreWhitespace = false;
				comma = false;
				out.print(c);
			}
		}
		out.println();
	}
}

