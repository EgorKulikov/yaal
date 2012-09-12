package on2012_09.on2012_09_September_Challenge_2012.Chef_World;



import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefWorld {
	long[] matrix;
	Matrix vector;
	long[][] powers;

	private static final long MOD = (long) (1e9 + 7);

	{
		Matrix.mod = MOD;
		long[][] data = new long[4][4];
		data[0][0] = data[0][1] = data[1][0] = data[2][0] = data[2][1] = data[2][2] = data[2][3] = data[3][2] = 1;
		matrix = Matrix.convert(data);
		vector = new Matrix(4, 1);
		vector.data[1][0] = vector.data[2][0] = 1;
		vector.data[0][0] = 2;
		powers = new long[60][16];
		powers[0] = matrix;
		for (int i = 1; i < 60; i++)
			Matrix.fastMultiply(powers[i], powers[i - 1], powers[i - 1], MOD, 4);
	}

	long[] power(long exponent) {
		long[] result = new long[16];
		long[] other = new long[16];
		for (int i = 0; i < 16; i += 5)
			result[i] = 1;
		for (int i = 0; i < 60; i++) {
			if ((exponent >> i & 1) == 1) {
				Matrix.fastMultiply(other, result, powers[i], MOD, 4);
				long[] temp = other;
				other = result;
				result = temp;
			}
		}
		return result;
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long count = in.readLong();
		if (count == 1) {
			out.printLine(0);
			return;
		}
		Matrix result = Matrix.multiply(new Matrix(Matrix.convert(power(count - 2), 4)), vector);
		long f0n1 = result.data[1][0];
		long f1n3 = result.data[2][0];
		long f1n4 = result.data[3][0];
		long f1n2 = f0n1 + f1n3 + f1n4;
		long f1n1 = f1n2 + f1n3;
		long answer = (f1n1 + f1n2) % MOD;
		out.printLine(answer);
	}
}
