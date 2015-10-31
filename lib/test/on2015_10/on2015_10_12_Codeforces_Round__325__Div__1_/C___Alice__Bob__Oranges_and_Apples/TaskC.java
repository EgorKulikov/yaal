package on2015_10.on2015_10_12_Codeforces_Round__325__Div__1_.C___Alice__Bob__Oranges_and_Apples;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long x = in.readLong();
		long y = in.readLong();
		if (IntegerUtils.gcd(x, y) != 1) {
			out.printLine("Impossible");
			return;
		}
		StringBuilder answer = new StringBuilder();
		while (true) {
			if (y == 1) {
				answer.append(x - 1);
				answer.append('A');
				break;
			}
			if (x == 1) {
				answer.append(y - 1);
				answer.append('B');
				break;
			}
			if (x > y) {
				answer.append(x / y);
				answer.append('A');
				x %= y;
			} else {
				answer.append(y / x);
				answer.append('B');
				y %= x;
			}
		}
		out.printLine(answer);
	}
}
