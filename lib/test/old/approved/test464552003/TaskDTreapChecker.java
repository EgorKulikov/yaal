package old.approved.test464552003;

import net.egork.utils.old.checker.Checker;
import net.egork.utils.old.io.old.InputReader;

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



