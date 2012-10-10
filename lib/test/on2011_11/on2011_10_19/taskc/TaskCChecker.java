package on2011_11.on2011_10_19.taskc;



import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;

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
		StringBuilder test = new StringBuilder();
		test.append("1000 ");
		for (int i = 0; i < 1000; i++)
			test.append("999999937 ");
		test.append("1000 ");
		for (int i = 0; i < 1000; i++)
			test.append("999999937 ");
		return Collections.singleton(new Test(test.toString(), "391040001", 0));
	}
}
