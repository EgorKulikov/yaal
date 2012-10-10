package on2012_04.on2012_3_11.taskc;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;
import net.egork.utils.io.InputReader;

import java.util.Collection;
import java.util.Collections;

public class TaskCChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
//		StringWriter sw = new StringWriter();
//		OutputWriter out = new OutputWriter(sw);
//		out.printLine(1000, 50);
//		Random rnd = new Random(239);
//		for (int i = 0; i < 1000; i++)
//			out.printLine(rnd.nextInt(1000000000) + 1, rnd.nextInt(1000000000) + 1, rnd.nextInt(1000000) + 1);
//		out.flush();
//		return Collections.singleton(new Test(sw.toString(), "", 0));
		return Collections.emptyList();
	}
}
