package on2012_05.on2012_4_6.taskb;



import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;

import java.util.Collection;
import java.util.Collections;

public class TaskBChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 1e-6;
	}

	public Collection<? extends Test> generateTests() {
		return Collections.emptyList();
	}
}
