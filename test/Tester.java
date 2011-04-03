import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Tester {
	private static enum Verdict {
			OK, WA, RTE
		}

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		int testCase = 0;
		List<Verdict> verdicts = new ArrayList<Verdict>();
		long maximalTime = 0;
		boolean ok = true;
		for (Test test : Tests.TESTS) {
			System.out.println("Test #" + testCase + ":");
			InputReader in = new StringInputReader(test.getInput());
			StringWriter out = new StringWriter(test.getExpectedOutput() == null ? 0 : test.getExpectedOutput().length());
			System.out.println("Input:");
			System.out.println(test.getInput());
			System.out.println("Expected output:");
			System.out.println(test.getExpectedOutput());
			System.out.println("Execution result:");
			long time = System.currentTimeMillis();
			try {
				Main.run(in, new PrintWriter(out));
				time = System.currentTimeMillis() - time;
				maximalTime = Math.max(time, maximalTime);
				String result = out.getBuffer().toString();
				System.out.println(result);
				System.out.print("Verdict: ");
				String checkResult = MainChecker.check(new StringInputReader(test.getInput()),
					new StringInputReader(result), new StringInputReader(test.getExpectedOutput()));
				if (checkResult == null) {
					System.out.print("OK");
					verdicts.add(Verdict.OK);
				} else {
					System.out.print("WA (" + checkResult + ")");
					verdicts.add(Verdict.WA);
					ok = false;
				}
				System.out.printf(" in %.3f s.\n", time / 1000.);
			} catch (Throwable e) {
				System.out.println("Exception thrown:");
				e.printStackTrace(System.out);
				verdicts.add(Verdict.RTE);
				ok = false;
			}
			testCase++;
			System.out.println("------------------------------------------------------------------");
		}
		System.out.println("==================================================================");
		System.out.println("Test results:");
		if (ok)
			System.out.printf("All test passed in %.3f s.\n", maximalTime / 1000.);
		else {
			for (int i = 0; i < verdicts.size(); i++)
				System.out.println("Test #" + i + ": " + verdicts.get(i));
		}

	}
}
