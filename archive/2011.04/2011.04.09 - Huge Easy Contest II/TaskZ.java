package April2011.UVaHugeEasyContestII;

import net.egork.datetime.Date;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TaskZ implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int exceptionalCount = in.readInt();
		Map<Date, Boolean> exceptionalDates = new HashMap<Date, Boolean>();
		for (int i = 0; i < exceptionalCount; i++) {
			Date date = Date.parse(in.readString(), "yyyy-mm-dd");
			exceptionalDates.put(date, in.readCharacter() == 'W');
		}
		Date start = Date.parse(in.readString(), "yyyy-mm-dd");
		Date end = Date.parse(in.readString(), "yyyy-mm-dd");
		int result = 0;
		if (start.compareTo(end) <= 0) {
			while (!start.equals(end)) {
				if (exceptionalDates.containsKey(start)) {
					if (exceptionalDates.get(start))
						result++;
				} else if (start.getWeekday() < 5)
					result++;
				start = start.next();
			}
			if (exceptionalDates.containsKey(start)) {
				if (exceptionalDates.get(start))
					result++;
			} else if (start.getWeekday() < 5)
				result++;
		}
		out.println("Case " + testNumber + ": " + result);
	}
}

