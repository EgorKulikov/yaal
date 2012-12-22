package on2011_11.on2011_10_9.taskd;



import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;

import java.util.Collection;
import java.util.Collections;

public class TaskDChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		StringBuilder builder = new StringBuilder();
		builder.append("100000\n");
		for (int i = 0; i < 100000; i++)
			builder.append(1000000000000000000L).append("\n");
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < 100000; i++)
			output.append("48898080\n");
		return Collections.singleton(new Test(builder.toString(), output.toString(), 0));
//		return Collections.emptyList();
	}
}
