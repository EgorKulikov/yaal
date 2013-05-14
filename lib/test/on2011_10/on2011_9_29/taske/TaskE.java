package on2011_10.on2011_9_29.taske;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class TaskE {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		String[] languages = IOUtils.readStringArray(in, count);
		List<Long> divisors = IntegerUtils.getDivisors(count);
		Collections.sort(divisors);
		List<Long> answer = new ArrayList<Long>();
		for (long divisor : divisors) {
			Set<String> was = new HashSet<String>();
			boolean good = true;
			for (int i = 0; i < divisor && good; i++) {
				String current = null;
				int delta = (int) (i * (count / divisor));
				for (int j = 0; j < count / divisor && good; j++) {
					if (languages[delta + j].equals("unknown"))
						continue;
					if (current == null)
						current = languages[delta + j];
					else if (!current.equals(languages[delta + j]))
						good = false;
				}
				if (current != null) {
					if (was.contains(current))
						good = false;
					was.add(current);
				}
			}
			if (good)
				answer.add(divisor);
		}
		if (answer.isEmpty())
			out.println("Igor is wrong.");
		else {
			out.print(answer.get(0));
			for (int i = 1; i < answer.size(); i++)
				out.print(" " + answer.get(i));
			out.println();
		}
	}
}
