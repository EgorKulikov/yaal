package on2011_12.on2011_11_12.taskr;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskR {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int count = in.readInt();
		int mod = 1;
		for (int i = 0; i < count; i++)
			mod *= 10;
		String answer = Long.toString(IntegerUtils.power(n, n, mod));
		if (n > 9 || Math.pow(n, n) >= mod) {
			while (answer.length() < count)
				answer = "0" + answer;
		}
		out.printLine(answer);
	}
}
