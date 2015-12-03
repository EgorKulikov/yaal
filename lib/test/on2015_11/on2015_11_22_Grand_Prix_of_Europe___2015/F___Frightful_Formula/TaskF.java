package on2015_11.on2015_11_22_Grand_Prix_of_Europe___2015.F___Frightful_Formula;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	private static final long MOD = (long) (1e6 + 3);
	private long[] fct;
	private long[] invf;

	public void solve(int testNumber, InputReader in, OutputWriter out) {

		int n = in.readInt() - 1;
		long a = in.readInt();
		long b = in.readInt();
		long c = in.readInt();
		long[] l = new long[n];
		long[] t = new long[n];
		in.readInt();
		for (int i = 0; i < n; i++) {
			l[i] = in.readInt();
		}
		in.readInt();
		for (int i = 0; i < n; i++) {
			t[i] = in.readInt();
		}

		long[] aa = new long[n + 1];
		long[] bb = new long[n + 1];
		aa[0] = 1; bb[0] = 1;
		for (int i = 0; i < n; i++) {
			aa[i + 1] = (aa[i] * a) % MOD;
			bb[i + 1] = (bb[i] * b) % MOD;
		}
		fct = new long[2 * n + 1];
		invf = new long[2 * n + 1];
		fct[0] = 1; invf[0] = 1;
		for (int i = 0; i < 2 * n; i++) {
			fct[i + 1] = (fct[i] * (i + 1)) % MOD;
			invf[i + 1] = IntegerUtils.power(fct[i + 1], MOD - 2, MOD);
		}

//		n = 3;

		long res = 0;
		for (int i = 0; i < n; i++) {
			long s = c(2 * n - i - 2, n - 1);
//			System.out.println(n + " " + (i + 1) + " " + s);
			long k1 = aa[n] * bb[n - i - 1] % MOD;
			k1 *= l[i];
			k1 %= MOD;
			long k2 = bb[n] * aa[n - i - 1] % MOD;
			k2 *= t[i];
			k2 %= MOD;
			res += s * (k1 + k2);
			res %= MOD;
		}

//		long[][] tt = new long[n][n];
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
////				tt[i][j] = a * (j == 0 ? l[i] : tt[i][j - 1]) +
////						b * (i == 0 ? t[j] : tt[i - 1][j]) + c;
//				tt[i][j] = a * (j == 0 ? 0 : tt[i][j - 1]) +
//						b * (i == 0 ? 0 : tt[i - 1][j]) + 1;
//				tt[i][j] %= MOD;
//			}
//			System.out.println(Arrays.toString(tt[i]));
//		}


		long ccc = 0;
		long qq = 1;
		for (int i = 0; i <= 2 * n - 2; i++) {
			ccc += qq;
			ccc %= MOD;
//			System.out.println(qq + " " + ccc);
			qq *= (a + b);
			qq %= MOD;
			if (i >= n - 1) {
				long r = c(i, n - 1);
				long y = aa[n] * bb[i + 1 - n] + aa[i + 1 - n] * bb[n];
				y %= MOD;
				r *= y;
				r %= MOD;
				qq = qq - r + MOD;
				qq %= MOD;
			}
		}
//		System.out.println(ccc);

		ccc *= c;
		ccc %= MOD;

		res += ccc;
		res %= MOD;

		out.printLine(res);
	}

	private long c(int n, int k) {
		long res = fct[n];
		res *= invf[k];
		res %= MOD;
		res *= invf[n - k];
		res %= MOD;
		return res;
	}
}
