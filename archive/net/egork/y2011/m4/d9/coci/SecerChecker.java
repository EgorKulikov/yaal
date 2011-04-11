package net.egork.y2011.m4.d9.coci;

import net.egork.utils.checker.Checker;
import net.egork.utils.io.InputReader;

public class SecerChecker extends Checker {
	@Override
	public String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {
		return tokenCheck(expectedOutput, actualOutput);
	}

	@Override
	public double getCertainty() {
		return 0;
	}
}

