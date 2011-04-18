package Timus.Part8;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1787 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int flowPerMinute = in.readInt();
		int minuteCount = in.readInt();
		int[] carCount = in.readIntArray(minuteCount);
		int result = 0;
		for (int car : carCount) {
			result += car;
			result = Math.max(result - flowPerMinute, 0);
		}
		out.println(result);
	}
}

