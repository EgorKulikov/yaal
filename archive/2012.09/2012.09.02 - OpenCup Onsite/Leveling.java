package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.Random;

public class Leveling {
	public static void main(String[] args) {
		Random random = new Random(5431754315134L);
		while (true) {
			int n = random.nextInt(3) + 1;
			int a = random.nextInt(10) + 1;
			int b = random.nextInt(10) + 1;
			long[] h = new long[n];
			for (int i = 0; i < n; ++i) h[i] = random.nextInt(21) - 10;
			new Leveling().doit(n, a, b, h);
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long a = in.readLong();
		long b = in.readLong();
		long[] h = new long[n];
		for (int i = 0; i < n; ++i) {
			h[i] = in.readLong();
		}
		long answer = doit(n, a, b, h);
		out.printLine(answer);
	}

	private long doit(int n, long a, long b, long[] h) {
		long g = BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).longValue();
		for (int i = 0; i < n; ++i) {
			if (h[i] % g != 0) {
				return -1;
			}
			h[i] /= g;
		}
		a /= g;
		b /= g;
		if (a > b) {
			long t = a;
			a = b;
			b = t;
		}
		ca = new long[n + 1];
		cb = new long[n + 1];
		long ka;
		long kb;
		if (a == 1) {
			ka = 1;
			kb = 0;
		} else if (b == 1) {
			ka = 0;
			kb = 1;
		} else {
			ka = BigInteger.valueOf(a).modInverse(BigInteger.valueOf(b)).longValue();
			if ((1 - ka * a) % b != 0)
				throw new RuntimeException();
			kb = (1 - ka * a) / b;
		}
		p = a;
		q = b;
		findKoefs(ca, cb, ka, kb, p, q, 0, h[0]);
		for (int i = 1; i < n; ++i) {
			findKoefs(ca, cb, ka, kb, p, q, i, h[i] - h[i - 1]);
		}
		findKoefs(ca, cb, ka, kb, p, q, n, -h[n - 1]);
		long sum = 0;
		for (long x : ca) sum += x;
		if (sum % q != 0) throw new RuntimeException();
		sgn = Long.signum(sum);
		long sumByQ = sum / q;
		heap = new int[n + 1];
		nHeap = 0;
		for (int i = 0; i <= n; ++i) {
			heap[nHeap++] = i;
			heapUp(nHeap - 1);
		}
		long answer = 0;
		for (long i = 0; i < Math.abs(sumByQ); ++i) {
			long v = value(heap[0]);
			if (v == p + q) {
				answer += (p + q) * (Math.abs(sumByQ) - i);
				break;
			}
			ca[heap[0]] -= q * sgn;
			cb[heap[0]] += p * sgn;
			if (value(heap[0]) < v) throw new RuntimeException();
			heapDown(0);
		}
		for (long x : ca) answer += Math.abs(x);
		for (long x : cb) answer += Math.abs(x);
		if (answer % 2 != 0) throw new RuntimeException();
		return answer / 2;
	}

	private void heapDown(int at) {
		while (true) {
			int i = at;
			if (2 * at + 1 < nHeap && value(heap[2 * at + 1]) < value(heap[i]))
				i = 2 * at + 1;
			if (2 * at + 2 < nHeap && value(heap[2 * at + 2]) < value(heap[i]))
				i = 2 * at + 2;
			if (at == i) break;
			swap(at, i);
			at = i;
		}
	}

	private void heapUp(int at) {
		while (at > 0 && value(heap[at]) < value(heap[(at - 1) / 2])) {
			swap(at, (at - 1) / 2);
			at = (at - 1) / 2;
		}
	}

	private void swap(int a, int b) {
		int t = heap[a];
		heap[a] = heap[b];
		heap[b] = t;
	}

	long sgn;
	long[] ca;
	long[] cb;
	int[] heap;
	int nHeap;
	long p;
	long q;

	long value(int at) {
		return Math.abs(ca[at] - q * sgn) + Math.abs(cb[at] + p * sgn) - Math.abs(ca[at]) - Math.abs(cb[at]);
	}

	private void findKoefs(long[] ca, long[] cb, long ka, long kb, long p, long q, int i, long zz) {
		long a;
		long b;
		a = ka * zz;
		b = kb * zz;
		long z = a / q;
		a -= q * z;
		b += p * z;
		a -= 3 * q;
		b += 3 * p;
		long ba = a;
		long bb = b;
		for (int j = 0; j < 7; ++j) {
			a += q;
			b -= p;
			if (Math.abs(a) + Math.abs(b) < Math.abs(ba) + Math.abs(bb)) {
				ba = a;
				bb = b;
			}
		}
		a = ba;
		b = bb;
		ca[i] = a;
		cb[i] = b;
	}
}


