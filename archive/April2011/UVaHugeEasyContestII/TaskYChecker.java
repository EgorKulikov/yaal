package April2011.UVaHugeEasyContestII;

import net.egork.utils.checker.Checker;
import net.egork.utils.io.InputReader;

public class TaskYChecker extends Checker {
	@Override
	public String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {
		return strictCheck(expectedOutput, actualOutput);
	}

	@Override
	public double getCertainty() {
		return 0;
	}
}

