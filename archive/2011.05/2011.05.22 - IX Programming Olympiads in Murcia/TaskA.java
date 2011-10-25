import net.egork.datetime.Date;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int month = in.readInt();
		int day = in.readInt();
		Date date = new Date(2011, month, day);
		out.println(Date.WEEKDAYS[date.getWeekday()]);
	}
}

