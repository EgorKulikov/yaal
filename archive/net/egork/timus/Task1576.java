package net.egork.timus;

import net.egork.datetime.Time;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;

public class Task1576 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int basicMonthly = in.readInt();
		int basicPerMinute = in.readInt();
		int combinedMonthly = in.readInt();
		int combinedFreeMinutes = in.readInt();
		int combinedPerMinute = in.readInt();
		int unlimitedMonthly = in.readInt();
		int callCount = in.readInt();
		int totalMinutes = 0;
		for (int i = 0; i < callCount; i++) {
			Time time = Time.parse(in.readString(), "mm:ss");
			int seconds = time.totalSeconds();
			if (seconds <= 6)
				continue;
			totalMinutes += (seconds + 59) / 60;
		}
		out.println("Basic:     " + (basicMonthly + basicPerMinute * totalMinutes));
		out.println("Combined:  " + (combinedMonthly + combinedPerMinute * Math.max(totalMinutes - combinedFreeMinutes, 0)));
		out.println("Unlimited: " + unlimitedMonthly);
	}
}

