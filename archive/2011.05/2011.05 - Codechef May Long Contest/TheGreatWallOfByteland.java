import net.egork.numbers.Matrix;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TheGreatWallOfByteland implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		Matrix.mod = 1000000007;
		Matrix matrix = new Matrix(3, 3);
		matrix.data[0][1] = 1;
		matrix.data[1][2] = 1;
		matrix.data[2] = new long[]{2, 4, 1};
		Matrix power = matrix.power(in.readInt());
		out.println(power.data[2][2]);
	}
}

