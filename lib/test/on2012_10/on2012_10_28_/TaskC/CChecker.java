package on2012_10.on2012_10_28_.TaskC;



import net.egork.chelper.checkers.TokenChecker;
import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;

public class CChecker implements Checker {
	public CChecker(String parameters) {
	}

	public Verdict check(String input, String expectedOutput, String actualOutput) {
		if (actualOutput.startsWith("-"))
			return Verdict.WA;
		return new TokenChecker("").check(input, expectedOutput, actualOutput);
	}
}
