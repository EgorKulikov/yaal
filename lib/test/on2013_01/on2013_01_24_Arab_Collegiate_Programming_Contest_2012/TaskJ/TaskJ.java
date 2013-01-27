package on2013_01.on2013_01_24_Arab_Collegiate_Programming_Contest_2012.TaskJ;



import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	static final long MOD = (long) (1e9 + 7);

	long[] matrix;

	{
		long[][] original = new long[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 10; j++)
				original[i][(10 * i + j) % 3]++;
		}
		matrix = Matrix.convert(original);
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long digitCount = in.readLong();
		char[] type = in.readString().toCharArray();
		long[] result = Matrix.power(matrix, Math.max(digitCount - 2, 0), MOD, 3);
		long answer = 0;
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < (digitCount == 1 ? 1 : 10); k++) {
				for (int l = 0; l < 10; l++) {
					boolean valid = true;
					for (int j = 0; j < 6; j++) {
						int remainder = (i * 100 + k * 10 + l) % (j + 1);
						if (remainder == 0 && type[j] == '0' || remainder != 0 && type[j] == '1')
							valid = false;
					}
					if (valid)
						answer += result[i];
				}
			}
		}
		answer %= MOD;
		out.printLine(answer);
    }
}
