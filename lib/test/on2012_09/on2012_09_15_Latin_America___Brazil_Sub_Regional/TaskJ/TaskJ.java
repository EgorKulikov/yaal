package on2012_09.on2012_09_15_Latin_America___Brazil_Sub_Regional.TaskJ;



import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Set;

public class TaskJ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted()) {
			throw new UnknownError();
		}
		int countFirst = in.readInt();
		int countSecond = in.readInt();
		String[] first = IOUtils.readStringArray(in, countFirst);
		String[] second = IOUtils.readStringArray(in, countSecond);
		Set<String> wasFirst = new EHashSet<String>();
		Set<String> wasSecond = new EHashSet<String>();
		for (String s : first) {
			if (go(s, first, second, wasFirst, wasSecond)) {
				out.printLine("S");
				return;
			}
		}
		out.printLine("N");
	}

	private boolean go(String s, String[] first, String[] second, Set<String> wasFirst, Set<String> wasSecond) {
		if (s.isEmpty())
			return true;
		if (wasFirst.contains(s))
			return false;
		wasFirst.add(s);
		for (String t : second) {
			if (s.length() >= t.length()) {
				if (s.startsWith(t) && go(s.substring(t.length()), first, second, wasFirst, wasSecond))
					return true;
			} else {
				if (t.startsWith(s) && go(t.substring(s.length()), second, first, wasSecond, wasFirst))
					return true;
			}
		}
		return false;
	}
}
