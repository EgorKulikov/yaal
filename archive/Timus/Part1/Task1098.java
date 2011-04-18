package Timus.Part1;

import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Task1098 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String text = in.readText().replaceAll("\\n", "");
		char last = text.charAt((int) MiscUtils.josephProblem(text.length(), 1999));
		if (last == '?')
			out.println("Yes");
		else if (last == ' ')
			out.println("No");
		else
			out.println("No comments");
	}
}

