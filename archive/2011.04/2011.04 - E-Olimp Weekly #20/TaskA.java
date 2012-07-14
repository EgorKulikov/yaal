package April2011.EOlimpWeekly20;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		out.println(IntegerUtils.sumDigits(Integer.toString(Math.abs(in.readInt()))));
	}
}

