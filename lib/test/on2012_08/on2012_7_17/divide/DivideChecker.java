package on2012_08.on2012_7_17.divide;



import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;

import java.util.Collection;
import java.util.Collections;

public class DivideChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 1e-2;
	}

	public Collection<? extends Test> generateTests() {
		return Collections.emptyList();
	}
}
