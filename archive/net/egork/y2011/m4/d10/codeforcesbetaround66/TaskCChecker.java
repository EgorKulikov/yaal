package net.egork.y2011.m4.d10.codeforcesbetaround66;

import net.egork.utils.checker.Checker;
import net.egork.utils.io.inputreader.InputReader;

public class TaskCChecker extends Checker {
	@Override
	public String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {
		return tokenCheck(expectedOutput, actualOutput);
	}

	@Override
	public double getCertainty() {
		return 0;
	}
}


