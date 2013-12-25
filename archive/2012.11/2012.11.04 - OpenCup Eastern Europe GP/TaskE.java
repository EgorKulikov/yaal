package net.egork;

import net.egork.numbers.FastFourierTransform;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	static final int MODULO = 30011;

	String expr;
	int pos;
	int p;
	int generator;
	int[] genPow;
	int[] genLog;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		p = in.readInt();
		genPow = new int[p - 1];
		genLog = new int[p];
		genLog[0] = -1;
		for (generator = 1;; ++generator) {
			int cur = 1;
			boolean ok = true;
			genPow[0] = cur;
			genLog[cur] = 0;
			for (int pow = 1; pow < p - 1 && ok; ++pow) {
				cur = cur * generator % p;
				genPow[pow] = cur;
				genLog[cur] = pow;
				if (cur == 1) ok = false;
			}
			if (ok) break;
		}
		expr = in.next() + "$";
		int[] res = parseExpr();
		if (expr.charAt(pos) != '$') throw new RuntimeException();
		out.printLine(res[0]);
	}

	private int[] parseExpr() {
		switch (expr.charAt(pos)) {
			case '(':
				++pos;
				int[] r1 = parseExpr();
				char op = expr.charAt(pos);
				++pos;
				int savedChar = expr.charAt(pos);
				int[] r2 = parseExpr();
				if (expr.charAt(pos) != ')') throw new RuntimeException();
				++pos;
				switch (op) {
					case '+':
						return add(r1, r2);
					case '*':
						return mul(r1, r2);
					case '^':
						return pow(r1, savedChar - '0');
					default:
						throw new RuntimeException();
				}

			default:
				char ch = expr.charAt(pos);
				++pos;
				if (ch >= '0' && ch <= '9') {
					return digit(ch - '0');
				} else if (ch >= 'a' && ch <= 'z') {
					return variable();
				} else throw new RuntimeException();
		}
	}

	private int[] mul(int[] a, int[] b) {
		int[] res = new int[p];
		for (int i = 0; i < p; ++i) {
			res[0] = (res[0] + a[0] * b[i]) % MODULO;
			if (i > 0)
				res[0] = (res[0] + a[i] * b[0]) % MODULO;
		}
		long[] aa = new long[p - 1];
		long[] bb = new long[p - 1];
		for (int i = 0; i < p - 1; ++i) {
			aa[i] = a[genPow[i]];
			bb[i] = b[genPow[i]];
		}
		long[] cc = multiply(aa, bb);
		for (int i = 0; i < cc.length; ++i) {
			int index = genPow[i % (p - 1)];
			res[index] = (int) ((res[index] + cc[i]) % MODULO);
		}
		return res;
	}

	private int[] add(int[] a, int[] b) {
		long[] aa = toLongArr(a);
		long[] bb = toLongArr(b);
		long[] cc = multiply(aa, bb);
		int[] res = new int[p];
		for (int i = 0; i < cc.length; ++i) {
			res[i % p] = (int) ((res[i % p] + cc[i]) % MODULO);
		}
		return res;
	}

	private long[] multiply(long[] aa, long[] bb) {
		//return fourierMultiply(aa, bb);
		/*long[] res = new long[aa.length + bb.length - 1];
		for (int i = 0; i < aa.length; ++i) {
			for (int j = 0; j < bb.length; ++j) {
				res[i + j] += aa[i] * bb[j];
			}
		}
		return res;*/
		return internalMultiply(aa, 0, aa.length, bb, 0, bb.length);
	}

	private long[] fourierMultiply(long[] aa, long[] bb) {
		for (int i = 0; i < aa.length; ++i) {
			if (2 * aa[i] > MODULO) {
				aa[i] -= MODULO;
			}
		}
		for (int i = 0; i < bb.length; ++i) {
			if (2 * bb[i] > MODULO) {
				bb[i] -= MODULO;
			}
		}
		long[] res = FastFourierTransform.multiply(aa, bb);
		for (int i = 0; i < res.length; ++i) {
			res[i] %= MODULO;
			if (res[i] < 0) res[i] += MODULO;
		}
		return res;
	}

	private long[] internalMultiply(long[] aa, int aStart, int aLen, long[] bb, int bStart, int bLen) {
		if (aLen < 0 || bLen < 0) throw new RuntimeException();
		if (aLen < 1000) {
			long[] aaC = new long[aLen];
			System.arraycopy(aa, aStart, aaC, 0, aLen);
			long[] bbC = new long[bLen];
			System.arraycopy(bb, bStart, bbC, 0, bLen);
			return fourierMultiply(aaC, bbC);
		}
		int half = (Math.max(aLen, bLen) + 1) / 2;
		long[] pPlusQ = new long[half];
		for (int i = 0; i < aLen; ++i) {
			if (i < half) {
				pPlusQ[i] += aa[aStart + i];
			} else {
				pPlusQ[i - half] += aa[aStart + i];
			}
		}
		long[] rPlusS = new long[half];
		for (int i = 0; i < bLen; ++i) {
			if (i < half) {
				rPlusS[i] += bb[bStart + i];
			} else {
				rPlusS[i - half] += bb[bStart + i];
			}
		}
		long[] complexProd = internalMultiply(pPlusQ, 0, half, rPlusS, 0, half);
		long[] pr = internalMultiply(aa, aStart + half, aLen - half, bb, bStart + half, bLen - half);
		long[] qs = internalMultiply(aa, aStart, half, bb, bStart, half);
		long[] res = new long[aLen + bLen - 1];
		for (int i = 0; i < pr.length; ++i) {
			if (i + 2 * half < res.length)
				res[i + 2 * half] += pr[i];
			if (i + half < res.length)
				res[i + half] -= pr[i];
		}
		for (int i = 0; i < qs.length; ++i) {
			if (i < res.length)
				res[i] += qs[i];
			if (i + half < res.length)
				res[i + half] -= qs[i];
		}
		for (int i = 0; i < complexProd.length; ++i) {
			if (i + half < res.length)
				res[i + half] += complexProd[i];
		}
		for (int i = 0; i < res.length; ++i) {
			res[i] %= MODULO;
			if (res[i] < 0) res[i] += MODULO;
		}
		return res;
	}

	private long[] toLongArr(int[] a) {
		long[] res = new long[a.length];
		for (int i = 0; i < a.length; ++i) {
			res[i] = a[i];
		}
		return res;
	}

	private int[] pow(int[] a, int k) {
		int[] res = new int[p];
		for (int i = 0; i < p; ++i) {
			int j = 1 % p;
			for (int l = 0; l < k; ++l) j = j * i % p;
			res[j] = (res[j] + a[i]) % MODULO;
		}
		return res;
	}

	private int[] digit(int what) {
		int[] res = new int[p];
		res[what % p] = 1 % MODULO;
		return res;
	}

	private int[] variable() {
		int[] res = new int[p];
		Arrays.fill(res, 1 % MODULO);
		return res;
	}
}
