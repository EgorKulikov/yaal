import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class Numbers implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long size = in.readInt();
		long zeroCount = 0;
		long oneCount = 0;
		for (int i = 0; i < size; i++) {
			int number = in.readInt();
			if (number == 0)
				zeroCount++;
			else if (number == 1)
				oneCount++;
		}
		long result = (zeroCount + oneCount) * size * size - zeroCount * oneCount * size;
		out.println(result);
	}
}

