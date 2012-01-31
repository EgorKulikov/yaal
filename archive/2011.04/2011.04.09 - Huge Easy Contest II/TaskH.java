package April2011.UVaHugeEasyContestII;

import net.egork.datetime.Time;
import net.egork.utils.old.io.old.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskH implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int busCount = in.readInt();
		int startTime = Time.parse(in.readString(), "hh:mm").totalMinutes();
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < busCount; i++) {
			int busTime = Time.parse(in.readString(), "hh:mm").totalMinutes();
			int travelTime = in.readInt();
			if (busTime >= startTime)
				answer = Math.min(answer, busTime - startTime + travelTime);
			else
				answer = Math.min(answer, busTime - startTime + travelTime + 24 * 60);
		}
		out.println("Case " + testNumber + ": " + answer);
	}
}

