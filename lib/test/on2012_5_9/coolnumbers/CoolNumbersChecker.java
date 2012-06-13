package on2012_5_9.coolnumbers;



import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class CoolNumbersChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		StringWriter sw = new StringWriter();
		OutputWriter out = new OutputWriter(sw);
		out.printLine(100000);
		Random rnd = new Random(239);
		for (int i = 0; i < 100000; i++) {
			out.print(rnd.nextInt(9) + 1);
			for (int j = 1; j < 40; j++)
				out.print(rnd.nextInt(10));
			out.printLine();
		}
		return Collections.singleton(new Test(sw.toString(), "", 0));
	}
}
