package on2012_5_29.taskb;



import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;

public class TaskBChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		out.printLine(100, 1);
		double xp = 0;
		double xq = 0;
		for (int i = 0; i < 50; i++) {
			xp += 2 * (4 * i + 2);
			xq += 2;
			out.printLine(4);
		}
		for (int i = 50; i < 100; i++) {
			xp += (4 * i + 2);
			xq += 1;
			out.printLine(1);
		}
		System.err.println(xp / xq / 4);
		return Collections.singleton(new Test(sw.toString(), "2666664000000000000\n" +
			"500 500", 0));
	}
}
