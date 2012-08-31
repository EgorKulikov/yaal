package on2011_11.on2011_10_28.taskg;



import net.egork.datetime.Date;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int day = in.readInt();
		int month = in.readInt();
		int year = in.readInt();
		Date date = new Date(year, month, day);
		date = date.next().next();
		out.printLine(date.day, date.month, date.year);
	}
}
