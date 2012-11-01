package on2012_10.on2012_10_28_.TaskJ;



import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String range = in.readString();
		String from = range.split("-")[0];
		String to = range.split("-")[1];
		int fromYear = convert(from);
		int toYear = convert(to);
		int answer = 0;
		for (int i = fromYear; i <= toYear; i++)
			answer = Math.max(answer, MiscUtils.convertToRoman(i).length());
		out.printLine(answer);
	}

	private int convert(String year) {
		String numeric = year.substring(0, year.length() - 2);
		if (year.charAt(year.length() - 2) == 'A')
			return Integer.parseInt(numeric) + 753;
		return 754 - Integer.parseInt(numeric);
	}
}
