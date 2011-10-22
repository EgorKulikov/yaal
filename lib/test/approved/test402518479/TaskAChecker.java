package approved.test402518479;

import net.egork.utils.checker.Checker;
import net.egork.utils.io.old.InputReader;
import net.egork.utils.test.Test;

import java.util.Collection;
import java.util.Collections;

public class TaskAChecker extends Checker {
	@Override
	public String check(net.egork.utils.io.old.InputReader input, InputReader expectedOutput, InputReader actualOutput) {
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

