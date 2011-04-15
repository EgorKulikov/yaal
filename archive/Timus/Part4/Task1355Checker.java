package Timus.Part4;

import net.egork.utils.checker.Checker;
import net.egork.utils.io.InputReader;

public class Task1355Checker extends Checker {
	@Override
	public String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {
		return tokenCheck(expectedOutput, actualOutput);
	}

	@Override
	public double getCertainty() {
		return 0;
	}
}

