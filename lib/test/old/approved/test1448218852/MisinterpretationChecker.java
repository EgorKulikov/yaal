package old.approved.test1448218852;

import net.egork.utils.old.checker.Checker;
import net.egork.utils.old.io.old.InputReader;
import net.egork.utils.old.test.Test;

import java.util.Collection;
import java.util.Collections;

public class MisinterpretationChecker extends Checker {
	@Override
	public String check(net.egork.utils.old.io.old.InputReader input, net.egork.utils.old.io.old.InputReader expectedOutput, InputReader actualOutput) {
		return tokenCheck(expectedOutput, actualOutput);
	}

	@Override
	public double getCertainty() {
		return 0;
	}

	@Override
	public Collection<Test> generateTests() {
		return Collections.emptySet();
	}
}

