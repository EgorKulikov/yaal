package on2015_10.on2015_10_12_Codeforces_Round__325__Div__1_.C___Alice__Bob__Oranges_and_Apples;



import net.egork.chelper.checkers.Checker;
import net.egork.chelper.tester.Verdict;
import net.egork.utils.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.InputMismatchException;


public class TaskCChecker implements Checker {
    public TaskCChecker(String parameters) {
    }

    public Verdict check(String input, String expectedOutput, String actualOutput) {
        InputReader in = new InputReader(new StringBufferInputStream(input));
        InputReader expected;
        if (expectedOutput == null)
            expected = null;
        else
            expected = new InputReader(new StringBufferInputStream(expectedOutput));
        InputReader actual = new InputReader(new StringBufferInputStream(actualOutput));
		try {
			return check(in, expected, actual);
		} catch (InputMismatchException e) {
			return new Verdict(Verdict.VerdictType.PE, e.getMessage());
		}
    }

    public Verdict check(InputReader in, InputReader expected, InputReader actual) {
		if (expected != null) {
			return expected.next().equals(actual.next()) ? Verdict.OK : Verdict.WA;
		}
		int a1 = 1;
		int a2 = 0;
		int b1 = 0;
		int b2 = 1;
		String answer = actual.next().replace("A", "A ").replace("B", "B ");
		String[] tokens = answer.split(" ");
		for (String token : tokens) {
			if (token.isEmpty()) {
				continue;
			}
			int times = Integer.parseInt(token.substring(0, token.length() - 1));
			if (token.endsWith("A")) {
				for (int i = 0; i < times; i++) {
					b1 += a1;
					b2 += a2;
				}
			} else {
				for (int i = 0; i < times; i++) {
					a1 += b1;
					a2 += b2;
				}
			}
		}
		int x = in.readInt();
		int y = in.readInt();
		if (x != a1 + b1 || y != a2 + b2) {
			return Verdict.WA;
		}
        return Verdict.OK;
    }
}
