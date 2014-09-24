package on2014_08.on2014_08_22_SnarkNews_Summer_Series_2014_Round_3.F___Exclusive_Chronometers;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long estimate = in.readInt();
		int[] at = IOUtils.readIntArray(in, count);
		long answer = 0;
		long mod = 1;
		for (int i = 0; i < at.length; i++) {
			answer = IntegerUtils.findCommon(answer, mod, at[i], i + 1);
			mod = IntegerUtils.lcm(mod, i + 1);
		}
		answer += mod * (estimate / mod);
		if (answer > estimate) {
			answer -= mod;
		}
		while (answer <= 0) {
			answer += mod;
		}
		if (Math.abs(answer + mod - estimate) < Math.abs(answer - estimate)) {
			answer += mod;
		}
		out.printLine(answer);
    }
}
