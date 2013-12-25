package on2012_10.on2012_10_27_Ural_Regional_School_Programming_Contest.G___The_tournament_of_intelligences;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] n = in.readString().toCharArray();
		ArrayUtils.reverse(n);
		for (int i = 0; i < n.length; i++)
			n[i] -= '0';
		int[] p = IntegerUtils.generatePrimes(50);
		long[][] power = new long[p.length][];
		for (int i = 0; i < p.length; i++)
			power[i] = IntegerUtils.generatePowers(10, n.length, p[i]);
		long[] current = new long[p.length];
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < n.length; j++)
				current[i] += n[j] * power[i][j];
			current[i] %= p[i];
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			if (type == 1) {
				int position = in.readInt() - 1;
				int newValue = in.readInt();
				for (int j = 0; j < p.length; j++) {
					current[j] += (newValue - n[position]) * power[j][position];
					current[j] %= p[j];
					if (current[j] < 0)
						current[j] += p[j];
				}
				n[position] = (char) newValue;
				continue;
			}
			long query = in.readLong();
			long mod = 1;
			long answer = 0;
			for (int j = 0; j < p.length; j++) {
				if (query % p[j] == 0) {
					if (mod == 1) {
						answer = current[j];
						mod = p[j];
						continue;
					}
					long x = IntegerUtils.reverse(mod % p[j], p[j]);
					long y = BigInteger.valueOf(p[j]).modInverse(BigInteger.valueOf(mod)).longValue();
					answer = BigInteger.valueOf(x).multiply(BigInteger.valueOf(current[j])).multiply(BigInteger.valueOf(mod)).
						add(BigInteger.valueOf(y).multiply(BigInteger.valueOf(answer)).multiply(BigInteger.valueOf(p[j]))).
						mod(BigInteger.valueOf(mod * p[j])).longValue();
					mod *= p[j];
				}
			}
			out.printLine(answer);
		}
	}
}
