package Timus.Part2;

import net.egork.utils.checker.Checker;
import net.egork.utils.io.InputReader;

public class Task1138Checker extends Checker {
	@Override
	public String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {
		return tokenCheck(expectedOutput, actualOutput);
	}

	@Override
	public double getCertainty() {
		return 0;
	}
}

