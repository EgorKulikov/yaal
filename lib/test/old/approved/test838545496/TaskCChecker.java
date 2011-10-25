package old.approved.test838545496;

import net.egork.utils.old.checker.Checker;

import java.util.Collections;
import java.util.Collection;
import net.egork.utils.old.test.Test;

public class TaskCChecker extends Checker {
	@Override
	public String check(net.egork.utils.old.io.old.InputReader input, net.egork.utils.old.io.old.InputReader expectedOutput, net.egork.utils.old.io.old.InputReader actualOutput) {
		return tokenCheck(expectedOutput, actualOutput);
	}

	@Override
	public double getCertainty() {
		return 0;
	}

	@Override
	public Collection<Test> generateTests() {
		return Collections.<Test>emptyList();
	}
}

