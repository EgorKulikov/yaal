package approved.test1230212088;

import net.egork.utils.checker.Checker;
import net.egork.utils.io.InputReader;
import net.egork.utils.test.Test;

import java.util.Collection;
import java.util.Collections;

public class TaskDChecker extends Checker {
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
		StringBuilder builder = new StringBuilder();
		builder.append("300000\n");
		for (int i = 0; i < 300000; i++)
			builder.append("1000000000\n");
		builder.append("300000\n");
		for (int i = 0; i < 300000; i++)
			builder.append("1 ").append(i + 1).append("\n");
		return Collections.singleton(new Test(builder.toString(), ""));
	}
}

