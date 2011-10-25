import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

public class Handsome implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int z = 30;
		boolean[] prime = new boolean[1000];
		int[] ps = new int[z];
		int pl = 0;
		double x = 1;
		Arrays.fill(prime, true);
		for (int n = 2; n < prime.length; n++) {
			if (!prime[n]) {
				continue;
			}
			if (pl < ps.length) {
				ps[pl++] = n;
				x *= n;
			}
			for (int m = 2 * n; m < prime.length; m += n) {
				prime[m] = false;
			}
		}
		int MAX = 1 << z;
		BigInteger[] prod = new BigInteger[z];
		double[] sqrt = new double[z];
		BigInteger prodCur = BigInteger.ONE;
		for (int i = 0; i < z; i++) {
			prodCur = prodCur.multiply(BigInteger.valueOf(ps[i]));
			prod[i] = prodCur;
			sqrt[i] = Math.sqrt(prodCur.doubleValue());
		}
		BigInteger[] ans = new BigInteger[z];
		double[] ansd = new double[z];
		int[] ansm = new int[z];
		Arrays.fill(ans, BigInteger.ZERO);
		for (int i = 0; i < 0*MAX; i++) {
			if ((i & ((1 << 20) - 1)) == 0) {
				System.out.println(i);
			}
			double d = 1;
//			BigInteger p = BigInteger.ONE;
			int k = 0;
			for (int j = 0; j < z; j++) {
				if ((i & (1 << j)) != 0) {
//					p = p.multiply(BigInteger.valueOf(ps[j]));
					d *= ps[j];
					k = Math.max(k, j);
				}
			}
			for (int j = k; j < z; j++) {
				if (d < sqrt[j] && d > ansd[j]) {
					ansd[j] = d;
					ansm[j] = i;
				}
//				if (p.multiply(p).compareTo(prod[j]) <= 0) {
//					if (p.compareTo(ans[j]) > 0) {
//						ans[j] = p;
//					}
//				}
			}
		}
//		System.out.println(x);
//		System.out.println(Math.sqrt(x));
//		System.out.println(Arrays.toString(ps));
//		System.out.println(Arrays.toString(ans));
//		System.out.println(Arrays.toString(ansd));
//		System.out.println(Arrays.toString(ansm));

		int[] mask = new int[]{0, 1, 4, 9, 11, 22, 75, 105, 449, 425, 1426, 2837, 4765, 2775, 21895, 57558, 87602, 145177, 123788, 694479, 677326, 1516496, 3363284, 2048443, 26968428, 24488513, 98733728, 144534809, 306037876, 857511142};
		for (int t = 1;; t++) {
			int n = in.readInt() - 1;
			if (n == -1) {
				break;
			}
			BigInteger p = BigInteger.ONE;
			for (int j = 0; j < z; j++) {
				if ((mask[n] & (1 << j)) != 0) {
					p = p.multiply(BigInteger.valueOf(ps[j]));
				}
			}
			out.println("Case #" + t + ": " + p);
		}
	}
}

