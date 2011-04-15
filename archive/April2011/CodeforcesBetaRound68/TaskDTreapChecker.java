package April2011.CodeforcesBetaRound68;

import net.egork.utils.checker.Checker;
import net.egork.utils.io.InputReader;

public class TaskDTreapChecker extends Checker {
	@Override
	public String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {
		return tokenCheck(expectedOutput, actualOutput);
	}

	@Override
	public double getCertainty() {
		return 0;
	}
}

