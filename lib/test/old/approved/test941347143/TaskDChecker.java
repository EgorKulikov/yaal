package old.approved.test941347143;

import net.egork.utils.old.checker.Checker;
import net.egork.utils.old.io.old.InputReader;
import net.egork.utils.old.test.Test;

import java.util.Collection;
import java.util.Collections;

public class TaskDChecker extends Checker {
	@Override
	public String check(net.egork.utils.old.io.old.InputReader input, InputReader expectedOutput, InputReader actualOutput) {
		return tokenCheck(expectedOutput, actualOutput);
	}

	@Override
	public double getCertainty() {
		return 1e-6;
	}

	@Override
	public Collection<Test> generateTests() {
		return Collections.<Test>emptyList();
	}
}

