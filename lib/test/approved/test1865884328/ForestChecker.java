package approved.test1865884328;

import net.egork.utils.checker.Checker;
import net.egork.utils.io.old.InputReader;
import java.util.Collections;
import java.util.Collection;
import net.egork.utils.test.Test;

public class ForestChecker extends Checker {
	@Override
	public String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {
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

