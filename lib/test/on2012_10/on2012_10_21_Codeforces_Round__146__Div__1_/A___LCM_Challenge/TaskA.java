package on2012_10.on2012_10_21_Codeforces_Round__146__Div__1_.A___LCM_Challenge;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int max = in.readInt();
		long answer = 0;
		for (int i = max; i >= max - 100 && i >= 1; i--) {
			for (int j = i; j >= max - 100 && j >= 1; j--) {
				for (int k = j; k >= max - 100 && k >= 1; k--)
					answer = Math.max(answer, IntegerUtils.lcm(i, IntegerUtils.lcm(j, k)));
			}
		}
		out.printLine(answer);
	}
}
