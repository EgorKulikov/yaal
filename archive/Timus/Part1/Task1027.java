package Timus.Part1;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1027 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String program = in.readText();
		program = program.replaceAll("[(][*]([^*]*[*]+[^)])*[^*]*[*]+[)]", "");
		if (program.contains("(*")) {
			out.println("NO");
			return;
		}
		int bracketLevel = 0;
		for (char c : program.toCharArray()) {
			if (c == '(')
				bracketLevel++;
			else if (c == ')') {
				bracketLevel--;
				if (bracketLevel < 0) {
					out.println("NO");
					return;
				}
			} else if ("=+-*/0123456789\n".indexOf(c) == -1 && bracketLevel != 0) {
				out.println("NO");
				return;
			}
		}
		if (bracketLevel == 0)
			out.println("YES");
		else
			out.println("NO");
	}
}

