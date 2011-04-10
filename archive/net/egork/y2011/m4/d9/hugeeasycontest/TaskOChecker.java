package net.egork.y2011.m4.d9.hugeeasycontest;

import net.egork.utils.checker.Checker;
import net.egork.utils.io.inputreader.InputReader;

public class TaskOChecker extends Checker {
	@Override
	public String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {
		while (true) {
			int ch1 = expectedOutput.read();
			while (ch1 == '\r')
				ch1 = expectedOutput.read();
			int ch2 = actualOutput.read();
			while (ch2 == '\r')
				ch2 = actualOutput.read();
			if (ch1 != ch2)
				return "WA";
			if (ch1 == -1)
				return null;
		}
	}

	@Override
	public double getCertainty() {
		return 0;
	}
}

