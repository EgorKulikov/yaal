package on2011_11.on2011_10_29.task1640;



import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;

import java.util.Collection;
import java.util.Collections;

public class Task1640Checker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		double x = actual.readDouble();
		double y = actual.readDouble();
		double r = actual.readDouble();
		boolean killed = false;
		int count = input.readInt();
		for (int i = 0; i < count; i++) {
			int curX = input.readInt();
			int curY = input.readInt();
			double distance = Math.hypot(x - curX, y - curY);
			if (distance > r + 1e-9)
				return new Verdict(Verdict.VerdictType.WA, i + " not inside");
			if (distance > r - 1e-9)
				killed = true;
		}
		if (killed)
			return Verdict.OK;
		else
			return new Verdict(Verdict.VerdictType.WA, "nobody killed");
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		return Collections.emptyList();
	}
}
