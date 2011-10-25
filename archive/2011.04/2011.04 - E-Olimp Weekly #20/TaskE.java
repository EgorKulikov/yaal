package April2011.EOlimpWeekly20;

import net.egork.numbers.Matrix;
import net.egork.utils.Exit;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.InputMismatchException;

public class TaskE implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		try {
			int a = in.readInt();
			int b = in.readInt();
			if (a == 0 && b == 0) {
				Exit.exit(in, out);
				return;
			}
			Matrix.mod = 1000000000;
			Matrix fibonacci = new Matrix(2, 2);
			fibonacci.data[0][0] = 1;
			fibonacci.data[0][1] = 1;
			fibonacci.data[1][0] = 1;
			Matrix bPower = fibonacci.fastPower(b + 2);
			Matrix aPower = fibonacci.fastPower(a + 1);
			long result = bPower.data[0][0] - aPower.data[0][0];
			if (result < 0)
				result += Matrix.mod;
			out.println(result);
		} catch (InputMismatchException e) {
			Exit.exit(in, out);
		}
	}
}

