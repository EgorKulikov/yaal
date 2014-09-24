package on2014_08.on2014_08_24_SnarkNews_Summer_Series_2014_Round_4.E___Shopping;



import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] mask = new int[edgeCount];
		for (int i = 0; i < edgeCount; i++) {
			from[i] = in.readInt() - 1;
			to[i] = in.readInt() - 1;
			String products = in.readString();
			for (char c : products.toCharArray()) {
				mask[i] |= 1 << (c - 'A');
			}
		}
		int time = in.readInt();
		long answer = 0;
		for (int i = 0; i < 16; i++) {
			long[][] matrix = new long[2 * count][2 * count];
			for (int j = 0; j < count; j++) {
				matrix[j + count][j] = 1;
			}
			for (int j = 0; j < edgeCount; j++) {
				matrix[to[j]][from[j]]++;
				if ((mask[j] & i) == mask[j]) {
					matrix[to[j]][from[j] + count]++;
				}
			}
			long[] asArray = Matrix.convert(matrix);
			long[] result = Matrix.sumPowers(asArray, time, MOD, 2 * count);
			if (Integer.bitCount(i) % 2 == 0) {
				answer += result[0];
			} else {
				answer -= result[0];
			}
		}
		answer %= MOD;
		answer += MOD;
		answer %= MOD;
		out.printLine(answer);
    }
}
