package on2012_04.on2012_3_23.tied;



import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;

public class TiedChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		out.printLine(10, 10000, 10000, 5000);
		for (int i = 0; i < 10; i++)
			out.printLine(1, 5000 + i);
		out.printLine(10000, 5000);
		for (int i = 0; i < 2500; i++) {
			out.printLine(0, 10000);
			out.printLine(0, 0);
			out.printLine(2, 0);
			if (i != 2499)
				out.printLine(2, 10000);
		}
		out.printLine(10000, 5000);
		out.flush();
		return Collections.singleton(new Test(sw.toString(), "10", 0));
	}
}
