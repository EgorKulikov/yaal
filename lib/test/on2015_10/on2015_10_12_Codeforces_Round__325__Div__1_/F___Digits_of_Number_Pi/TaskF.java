package on2015_10.on2015_10_12_Codeforces_Round__325__Div__1_.F___Digits_of_Number_Pi;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.string.SubstringAutomaton;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	private static final long MOD = (long) (1e9 + 7);

	long[] tens;
	SubstringAutomaton automaton;

	long[][] result;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		String x = in.readString();
		String y = in.readString();
		int sCount = s.length() - x.length() / 2 + 1;
		if (sCount <= 0) {
			out.printLine(0);
			return;
		}
		String[] samples = new String[sCount];
		for (int i = 0; i < samples.length; i++) {
			samples[i] = s.substring(i, i + x.length() / 2);
		}
		automaton = new SubstringAutomaton(samples, '0', '9');
		long answer = 0;
		tens = IntegerUtils.generatePowers(10, x.length() + 1, MOD);
		boolean separate = false;
		result = new long[x.length() + 1][automaton.ends.length];
		ArrayUtils.fill(result, -1);
		for (int i = 0; i < x.length(); i++) {
			if (separate) {
				for (int j = x.charAt(i) - '0' + 1; j < 10; j++) {
					answer += solve(x.substring(0, i) + j, x.length() - i - 1);
				}
				for (int j = 0; j < y.charAt(i) - '0'; j++) {
					answer += solve(y.substring(0, i) + j, x.length() - i - 1);
				}
			} else {
				if (x.charAt(i) != y.charAt(i)) {
					separate = true;
					for (int j = x.charAt(i) - '0' + 1; j < y.charAt(i) - '0'; j++) {
						answer += solve(x.substring(0, i) + j, x.length() - i - 1);
					}
				}
			}
		}
		answer += solve(x, 0);
		if (!x.equals(y)) {
			answer += solve(y, 0);
		}
		answer %= MOD;
		out.printLine(answer);
	}

	long solve(String s, int remaining) {
		int start = 0;
		for (int i = 0; i < s.length(); i++) {
			start = automaton.edges[start][s.charAt(i) - '0'];
			if (automaton.ends[start] != 0) {
				return tens[remaining];
			}
		}
		return go(remaining, start);
	}

	private long go(int remaining, int start) {
		if (result[remaining][start] != -1) {
			return result[remaining][start];
		}
		if (automaton.ends[start] != 0) {
			return result[remaining][start] = tens[remaining];
		}
		result[remaining][start] = 0;
		if (remaining == 0) {
			return 0;
		}
		for (int i = 0; i < 10; i++) {
			result[remaining][start] += go(remaining - 1, automaton.edges[start][i]);
		}
		return result[remaining][start] %= MOD;
	}
}
