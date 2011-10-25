package April2011.EOlympWeekly21;

import net.egork.datetime.Time;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskC implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int timeCount = in.readInt();
		Time[] times = new Time[timeCount];
		for (int i = 0; i < timeCount; i++) {
			int hours = in.readInt();
			int minutes = in.readInt();
			int seconds = in.readInt();
			times[i] = new Time(hours, minutes, seconds);
		}
		Arrays.sort(times);
		for (Time time : times)
			out.println(time.getHours() + " " + time.getMinutes() + " " + time.getSeconds());
	}
}

