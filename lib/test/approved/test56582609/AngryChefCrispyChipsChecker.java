package approved.test56582609;

import net.egork.utils.checker.Checker;
import net.egork.utils.io.InputReader;
import net.egork.utils.test.Test;

import java.util.Collection;
import java.util.Collections;

public class AngryChefCrispyChipsChecker extends Checker {
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
		StringBuilder test = new StringBuilder();
		test.append("100000 0\n");
		for (int i = 0; i < 100000; i++)
			test.append(i + "\n");
		test.append("100000\n");
		for (int i = 0; i < 100000; i++)
			test.append(i + " 99999\n");
		return Collections.singleton(new Test(test.toString(), ""));
	}
}

