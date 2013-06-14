package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class TaskE {

	private static final BigInteger MAX = BigInteger.valueOf((long) 2e18);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		BigInteger[] a = new BigInteger[n];
		for (int i = 0; i < n; i++) {
			a[i] = BigInteger.valueOf(in.readLong());
		}

		if (n == 1) {
			out.printLine("1 " + (a[0].longValue() + 1) + " 1");
			return;
		}

		if (a[0].equals(BigInteger.ZERO)) {
			int i = 0;
			while (i < n && a[i].equals(BigInteger.ZERO)) i++;
			out.printLine(i + " 1 1");
			return;
		}

		Set<BigInteger> possibleP = new HashSet<BigInteger>();
		BigInteger P = BigInteger.ZERO;
		for (int i = 0; i < n - 2; i++) {
			BigInteger q = a[i + 1].multiply(a[i + 1]).subtract(
				a[i].multiply(a[i + 2])
			);
			q = q.abs();
			if (P.equals(BigInteger.ZERO)) {
				P = q;
			} else {
				P = P.gcd(q);
			}
			if (!P.equals(BigInteger.ZERO)) {
				possibleP.add(P);
			}
		}
		BigInteger bigP = BigInteger.valueOf(1000000000);
		while (!bigP.isProbablePrime(1000)) bigP = bigP.add(BigInteger.ONE);

		possibleP.add(bigP);

		int res = 1;
		BigInteger bestP = bigP;
		BigInteger bestK = BigInteger.ONE;


//		System.out.println(possibleP);

		Set<BigInteger> kk = new HashSet<BigInteger>();

		for (BigInteger curP : possibleP) {
			try {
				BigInteger nom = a[1];
				BigInteger den = a[0];
				BigInteger gcd = nom.gcd(den);
				nom = nom.divide(gcd);
				den = den.divide(gcd);

				do {
					gcd = den.gcd(curP);
					curP = curP.divide(gcd);
				} while (!gcd.equals(BigInteger.ONE));

//				System.out.println(nom + " " + den + " " + curP);
				BigInteger k = nom.multiply(den.modInverse(curP));
				k = k.mod(curP);

				if (kk.contains(k))
					continue;
				kk.add(k);
//				System.out.println(k + " " + curP);

				BigInteger p = BigInteger.ZERO;
				BigInteger max = a[0];

				for (int i = 1; i < n; i++) {
					BigInteger aa = a[i - 1].multiply(k);
					BigInteger d = aa.subtract(a[i]);
					if (p.equals(BigInteger.ZERO)) {
						p = d;
					} else {
						p = p.gcd(d);
					}
					max = max.max(a[i]);
					if (!p.equals(BigInteger.ZERO) && p.compareTo(max) <= 0) break;
					if (p.equals(BigInteger.ZERO) ||
						a[i].equals(aa.mod(p))) {
						if (i + 1 > res && p.compareTo(MAX) <= 0) {
							res = i + 1;
							bestK = k;
							if (p.equals(BigInteger.ZERO)) {
								bestP = bigP;
							} else {
								bestP = p;
							}
						}
					}
				}
			} catch (ArithmeticException e) {
			}
		}
		bestK = bestK.mod(bestP);
		out.printLine(res + " " + bestP + " " + bestK);

	}
}
