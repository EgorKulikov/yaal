package approved.test1479782644;

import net.egork.datetime.Date;
import net.egork.utils.Solver;
import net.egork.utils.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int month = in.readInt();
		int day = in.readInt();
		Date date = new Date(2011, month, day);
		out.println(Date.WEEKDAYS[date.getWeekday()]);
	}
}

