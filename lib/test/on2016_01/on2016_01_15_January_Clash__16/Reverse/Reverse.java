package on2016_01.on2016_01_15_January_Clash__16.Reverse;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Matrix;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Reverse {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		if (k == 10) {
			out.printLine(1);
			return;
		}
		if (k == 3 || k == 9 || k == 1) {
			long answer = IntegerUtils.power(10, n, MOD) + k - 1;
			answer = answer * IntegerUtils.reverse(k, MOD) % MOD;
			out.printLine(answer);
			return;
		}
		if (k == 2 || k == 5) {
			long answer = 10 / k;
			if (n >= 2) {
				answer += (IntegerUtils.power(10, n - 1, MOD) - 1) * IntegerUtils.reverse(9, MOD) % MOD * (10 / k - 1) * 10 / k;
			}
			answer %= MOD;
			out.printLine(answer);
			return;
		}
		if (n <= 10) {
			long[][] current = new long[k][k];
			for (int i = 1; i < 10; i++) {
				current[i % k][i % k]++;
			}
			long answer = 1 + current[0][0];
			long[][] next = new long[k][k];
			int pow = 10 % k;
			for (int j = 1; j < n; j++) {
				ArrayUtils.fill(next, 0);
				for (int l = 0; l < k; l++) {
					for (int m = 0; m < k; m++) {
						for (int i = 0; i < 10; i++) {
							int nl = (l * 10 + i) % k;
							int nm = (m + i * pow) % k;
							next[nl][nm] += current[l][m];
							next[nl][nm] %= MOD;
						}
					}
				}
				answer += next[0][0];
				pow *= 10;
				pow %= k;
				long[][] temp = current;
				current = next;
				next = temp;
			}
			out.printLine(answer % MOD);
			return;
		}
		if (k == 4) {
			long answer = 59;
			answer += (IntegerUtils.power(10, n - 3, MOD) - 1) * IntegerUtils.reverse(9, MOD) % MOD * 20 * 25;
			answer %= MOD;
			out.printLine(answer);
			return;
		}
		if (k == 8) {
			long answer = 1392;
			answer += (IntegerUtils.power(10, n - 5, MOD) - 1) * IntegerUtils.reverse(9, MOD) % MOD * 100 * 125;
			answer %= MOD;
			out.printLine(answer);
			return;
		}
		if (k == 6) {
			long answer = 0;
			for (int i = 2; i < 10; i += 2) {
				for (int j = 0; j < 10; j += 2) {
					int s = (i + j) % 3;
					answer += (IntegerUtils.power(10, n - 1, MOD) - 1) * IntegerUtils.reverse(9, MOD) % MOD;
					if (s == 0) {
						answer += 2 * (n - 1);
					} else {
						answer -= n - 1;
					}
				}
			}
			answer %= MOD;
			if (answer < 0) {
				answer += MOD;
			}
			answer *= IntegerUtils.reverse(3, MOD);
			answer += 2;
			answer %= MOD;
			out.printLine(answer);
			return;
		}
		Matrix[] m = new Matrix[7];
		Matrix.mod = MOD;
		int t = 3;
		m[0] = Matrix.identityMatrix(49);
		for (int i = 1; i < 7; i++) {
			m[i] = new Matrix(49, 49);
			for (int j = 0; j < 7; j++) {
				for (int l = 0; l < 7; l++) {
					for (int o = 0; o < 10; o++) {
						int nj = (10 * j + o) % 7;
						int nl = (l + o * t) % 7;
						m[i].data[j * 7 + l][nj * 7 + nl]++;
					}
				}
			}
			m[i] = Matrix.multiply(m[i - 1], m[i]);
			t *= 10;
			t %= 7;
		}
		n--;
		int x = n / 6;
		Matrix sp = new Matrix(Matrix.convert(Matrix.sumPowers(Matrix.convert(m[6].data), x - 1, MOD, 49), 49));
		Matrix by = new Matrix(49, 49);
		for (int i = 0; i < 6; i++) {
			by = Matrix.add(m[i], by);
		}
		Matrix base = Matrix.multiply(sp, by);
		Matrix max = m[6].power(x);
		Matrix aBy = new Matrix(49, 49);
		for (int i = 0; i <= n % 6; i++) {
			aBy = Matrix.add(m[i], aBy);
		}
		base = Matrix.add(base, Matrix.multiply(max, aBy));
		long answer = 1;
		for (int i = 1; i < 10; i++) {
			int at = i % 7 * 7 + i % 7;
			answer += base.data[at][0];
		}
		out.printLine(answer % MOD);
	}
}
