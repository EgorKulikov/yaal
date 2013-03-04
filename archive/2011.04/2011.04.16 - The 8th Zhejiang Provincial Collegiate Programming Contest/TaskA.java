package April2011.ZJU8thZhejiangProvincialCollegiateProgrammingContest;

import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int number = in.readInt();
		if (number / 10 % 10 == 1 || number % 10 == 0 || number % 10 > 3)
			out.println(number + "th");
		else if (number % 10 == 1)
			out.println(number + "st");
		else if (number % 10 == 2)
			out.println(number + "nd");
		else
			out.println(number + "rd");
	}
}

