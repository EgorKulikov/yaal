package on2012_05.on2012_4_6.taska;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	private static final long MOD = 1000000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long yearCount = in.readLong();
		long answer;
		if (yearCount != 0)
			answer = IntegerUtils.power(2, yearCount - 1, MOD) * (IntegerUtils.power(2, yearCount, MOD) + 1) % MOD;
		else
			answer = 1;
		out.printLine(answer);
	}
}
