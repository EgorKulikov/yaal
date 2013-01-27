package on2011_10.on2011_9_30.chronicle;


import net.egork.chelper.task.Test;
import net.egork.datetime.Date;
import net.egork.utils.io.InputReader;

import java.util.*;

public class ChronicleChecker {
	public String check(InputReader input, InputReader expected, InputReader actual) {
		Set<Date> expectedSet = readSet(expected);
		Set<Date> actualSet = readSet(actual);
		if (!expectedSet.equals(actualSet))
			return "Wrong answer";
		return null;
	}

	private Set<Date> readSet(InputReader in) {
		Set<Date> dates = new HashSet<Date>();
		try {
			while (true) {
				String date = in.readLine();
				if (date.equals("No such date"))
					return new HashSet<Date>();
				dates.add(Date.parse(date, "dd/mm/yy"));
			}
		} catch (InputMismatchException e) {
			return dates;
		}
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		return Collections.emptyList();
	}
}
