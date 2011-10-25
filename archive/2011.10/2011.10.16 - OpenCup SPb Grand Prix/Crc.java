import net.egork.utils.Exit;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Crc implements Solver {
	public static long P = (1L << 32) + (1L << 26) + (1L << 15) + (1L << 7) + 1;

	static long mul(long a, long b) {
		long res = 0;
		for (int i = 0; i < 32; ++i) {
			if ((a & (1L << i)) != 0)
				res ^= b << i;
		}
		for (int i = 63; i >= 32; --i) {
			if ((res & (1L << i)) != 0) {
				res ^= P << (i - 32);
			}
		}
		return res;
	}

	static long[] pow;
	static long[] powBig;
	static int[] hashFirst;
	static int[] hashNext;

	static {
		pow = new long[1 << 16];
		long cur = 1;
		for (int i = 0; i < pow.length; ++i) {
			pow[i] = cur;
			cur = mul(cur, 2);
		}
		long p65536 = cur;
		cur = 1;
		powBig = new long[1 << 16];
		for (int i = 0; i < powBig.length; ++i) {
			powBig[i] = cur;
			cur = mul(cur, p65536);
		}
		hashFirst = new int[1 << 18];
		hashNext = new int[1 << 16];
		Arrays.fill(hashFirst, -1);
		for (int i = 0; i < powBig.length; ++i) {
			int chash = (int) (powBig[i] & (hashFirst.length - 1));
			hashNext[i] = hashFirst[chash];
			hashFirst[chash] = i;
		}
	}

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		String s = in.readLine();
		if (s.equals("0")) {
			Exit.exit(in, out);
			return;
		}
		s = s.replace('x', ' ').replace('^', ' ').replace('+', ' ');
		StringTokenizer tokenizer = new StringTokenizer(s);
		long input = 0;
		while (tokenizer.hasMoreTokens()) {
			int pow = Integer.parseInt(tokenizer.nextToken());
			input |= 1L << pow;
		}
		for (int i = 0; i < pow.length; ++i) {
			int cur = hashFirst[((int) (input & (hashFirst.length - 1)))];
			while (cur >= 0) {
				if (powBig[cur] == input) {
					long res = (((1L << 32) - 1) - i + cur * (1L << 16)) % ((1L << 32) - 1);
					out.println(res);
					break;
				}
				cur = hashNext[cur];
			}
			if (cur >= 0) break;
			input <<= 1;
			if (input >= (1L << 32))
				input ^= P;
		}
	}
}

