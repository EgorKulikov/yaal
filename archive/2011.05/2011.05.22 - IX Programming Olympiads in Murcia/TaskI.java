import net.egork.utils.Exit;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskI implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String number = in.readString();
		if ("0".equals(number)) {
			Exit.exit(in, out);
			return;
		}
		while (number.charAt(0) == '0')
			number = number.substring(1);
		int firstSquared;
		int zeroCount = (number.length() - 1) / 2;
		if (number.length() % 2 == 0)
			firstSquared = Integer.parseInt(number.substring(0, 2));
		else
			firstSquared = number.charAt(0) - '0';
		for (int i = 9; i >= 1; i--) {
			if (i * i <= firstSquared) {
				out.print(i);
				for (int j = 0; j < zeroCount; j++)
					out.print(0);
				out.println();
				return;
			}
		}
		throw new RuntimeException();
	}
}

