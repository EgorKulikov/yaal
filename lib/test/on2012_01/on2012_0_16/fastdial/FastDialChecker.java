package on2012_01.on2012_0_16.fastdial;



import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.Verdict;

import java.util.Collection;
import java.util.Collections;

public class FastDialChecker {
	public Verdict check(InputReader input, InputReader expected, InputReader actual) {
		return Verdict.UNDECIDED;
	}

	public double getCertainty() {
		return 1e-4;
	}

	public Collection<? extends Test> generateTests() {
		return Collections.emptyList();
	}
}
