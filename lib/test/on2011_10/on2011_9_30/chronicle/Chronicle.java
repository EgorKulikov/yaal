package on2011_10.on2011_9_30.chronicle;



import net.egork.datetime.Date;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Chronicle {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String date = in.readString();
		char[] pattern = "  /  /  ".toCharArray();
		Set<Date> correct = new HashSet<Date>();
		for (int i = 0; i < 3; i++) {
			pattern[i * 3] = 'y';
			pattern[i * 3 + 1] = 'y';
			for (int j = 0; j < 3; j++) {
				if (j == i)
					continue;
				pattern[j * 3] = 'm';
				pattern[j * 3 + 1] = 'm';
				int k = 3 - i - j;
				pattern[k * 3] = 'd';
				pattern[k * 3 + 1] = 'd';
				Date candidate = Date.parse(date, new String(pattern));
				if (candidate.year == 0)
					candidate = new Date(2100, candidate.month, candidate.day);
				else
					candidate = new Date(2000 + candidate.year, candidate.month, candidate.day);
				if (candidate.isValid())
					correct.add(candidate);
			}
		}
		if (correct.isEmpty()) {
			out.println("No such date");
			return;
		}
		for (Date valid : correct)
			out.println(valid.toString("dd/mm/yy"));
	}
}
