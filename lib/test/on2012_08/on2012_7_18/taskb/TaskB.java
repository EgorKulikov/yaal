package on2012_08.on2012_7_18.taskb;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int result = in.readInt();
		if (count == 1 && result == 1) {
			out.printLine(0);
			out.printLine("T");
			return;
		}
		char[] current = new char[count];
		char[] answer = new char[count];
		int minErrors = Integer.MAX_VALUE;
		for (int i = 1; i < result; i++) {
			if (IntegerUtils.gcd(i, result - i) != 1)
				continue;
			int top = result - i;
			int bottom = i;
			boolean good = true;
			int errors = 0;
			current[count - 1] = 'T';
			for (int j = count - 2; j >= 0; j--) {
				if (top == 0 || bottom == 0 || errors >= minErrors) {
					good = false;
					break;
				}
				if (top > bottom || top == bottom && current[j + 1] == 'B') {
					current[j] = 'T';
					top -= bottom;
				} else {
					current[j] = 'B';
					bottom -= top;
				}
				if (current[j] == current[j + 1])
					errors++;
			}
			if (good && top + bottom == 1 && errors < minErrors) {
				minErrors = errors;
				for (int j = 0; j < count; j++) {
					if (current[0] == 'T')
						answer[j] = current[j];
					else
						answer[j] = (char) ('T' + 'B' - current[j]);
				}
			}
		}
		if (minErrors == Integer.MAX_VALUE) {
			out.printLine("IMPOSSIBLE");
			return;
		}
		out.printLine(minErrors);
		out.printLine(answer);
	}
}
