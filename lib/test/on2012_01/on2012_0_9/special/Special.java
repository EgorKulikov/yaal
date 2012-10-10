package on2012_01.on2012_0_9.special;



import net.egork.datetime.Date;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Special {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int day = in.readInt();
		int month = in.readInt();
		int year = in.readInt();
		Date date = new Date(year, month, day);
		while (!isGood(date))
			date = date.next();
		out.printLine(date.day, date.month, date.year);
	}

	private boolean isGood(Date date) {
		return isGoodDay(date, "");
	}

	private boolean isGoodDay(Date date, String representation) {
		return date.day < 10 && isGoodMonth(date, representation + "0" + date.day) || isGoodMonth(date, representation + date.day);
	}

	private boolean isGoodMonth(Date date, String representation) {
		return date.month < 10 && isGoodYear(date, representation + "0" + date.month) || isGoodYear(date, representation + date.month);
	}

	private boolean isGoodYear(Date date, String representation) {
		return isGood(representation + date.year) || isGood(representation + date.year % 100 / 10 + date.year % 10);
	}

	private boolean isGood(String representation) {
		if (representation.length() % 2 == 0 && representation.substring(representation.length() / 2).equals(representation.substring(0, representation.length() / 2)))
			return true;
		return representation.equals(StringUtils.reverse(representation));
	}
}
