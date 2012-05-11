package on2012_4_11.taskd;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.*;

public class TaskDChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		List<Test> tests = new ArrayList<Test>();
		Random rnd = new Random(239);
		for (int i = 0; i < 100; i++) {
			StringWriter sw = new StringWriter();
			OutputWriter out = new OutputWriter(sw);
			out.printLine(10, rnd.nextInt(10) + 1, rnd.nextInt(10) + 1);
			for (int j = 0; j <= 10; j++)
				out.printLine(rnd.nextInt(50) + 1);
			out.printLine(50);
			for (int j = 1; j <= 50; j++)
				out.printLine(j);
			out.flush();
			if (i == 5)
				tests.add(new Test(sw.toString(), "", 0));
		}
		return tests;
//		return Collections.emptyList();
	}
}
