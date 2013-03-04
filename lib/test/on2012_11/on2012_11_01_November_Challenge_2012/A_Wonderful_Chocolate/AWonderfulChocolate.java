package on2012_11.on2012_11_01_November_Challenge_2012.A_Wonderful_Chocolate;



import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AWonderfulChocolate {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int width = in.readInt();
		long length = in.readLong();
		long[][] matrix = new long[1 << width][1 << width];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				int x = i & j;
				int y = ~i & ~j;
				boolean good = true;
				for (int k = 1; k < width; k++) {
					if ((x >> k & 1) == 1 && (x >> (k - 1) & 1) == 1) {
						good = false;
						break;
					}
					if ((y >> k & 1) == 1 && (y >> (k - 1) & 1) == 1) {
						good = false;
						break;
					}
				}
				if (good)
					matrix[i][j] = 1;
			}
		}
		long answer = 0;
		for (long v : Matrix.power(Matrix.convert(matrix), length - 1, MOD, matrix.length))
			answer += v;
		answer %= MOD;
		out.printLine(answer);
	}
}
