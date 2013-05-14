package on2012_02.on2012_1_17.task3;



import net.egork.collections.comparators.ReverseComparator;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.*;

public class Task3 {
	static final long MOD = (long)1e9 + 7;
	private static final int UP_TO = 20000;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int length = in.readInt();
		int[] period = IOUtils.readIntArray(in, count);
//		Tree tree = new Tree(period);
		long[] answer = new long[count - length + 1];
		Arrays.fill(answer, 1);
		boolean[] isPrime = IntegerUtils.generatePrimalityTable(UP_TO);
//		int countPrime = CollectionUtils.count(Array.wrap(isPrime), true);
//		int[] pp = new int[count + 1];
		for (int i = 2; i < UP_TO; i++) {
			if (!isPrime[i])
				continue;
			int maxPower = 0;
			long power = 1;
			while (power * i <= Integer.MAX_VALUE) {
				power *= i;
				maxPower++;
			}
			long[] arrPower = new long[maxPower + 1];
			arrPower[0] = 1;
			for (int j = 1; j <= maxPower; j++)
				arrPower[j] = arrPower[j - 1] * i;
			int[] ends = new int[maxPower + 1];
			Arrays.fill(ends, count);
			int curMax = 0;
			for (int j = count - 1; j >= 0; j--) {
				int curPower = 0;
				while (period[j] >= arrPower[curPower] * i * i && period[j] % (i * arrPower[curPower]) == 0 || period[j] == i * arrPower[curPower]) {
					curPower++;
				}
				if (curPower != 0)
					period[j] /= arrPower[curPower];
//				pp[j] = curPower;
				ends[curPower] = j - length + 1;
				curMax = Math.max(curMax, curPower);
				if (j <= count - length) {
					while (curMax != 0){
						if (ends[curMax] <= j) {
							answer[j] *= arrPower[curMax];
							if (answer[j] >= MOD)
								answer[j] %= MOD;
							break;
						} else
							curMax--;
					}
				}
			}
		}
		long mult = 1;
		Map<Integer, Integer> nums = new HashMap<Integer, Integer>();
		for (int i = count - 1; i >= 0; i--) {
			if (period[i] != 1) {
				if (!nums.containsKey(period[i]))
					mult = mult * period[i] % MOD;
				nums.put(period[i], i - length + 1);
			}
			if (i < count - length) {
				if (period[i + length] != 1 && nums.get(period[i + length]) == i + 1) {
					nums.remove(period[i + length]);
					mult = mult * IntegerUtils.reverse(period[i + length], MOD);
				}
			}
			if (i <= count - length) {
				answer[i] = answer[i] * mult % MOD;
			}
		}
//		for (int i = 0; i <= count - length; i++)
//			answer[i] = tree.get(i, i + length);
		out.printLine(Array.wrap(answer).toArray());
	}
}

class Tree {
	static final long MOD = (long)1e9 + 7;

	private BigInteger[] lcm;//, gcd, resultLCM, resultGCD;
	private int length;
	List<BigInteger> current;

	public Tree(int[] period) {
		length = period.length;
		int nodeCount = Integer.highestOneBit(length) << 2;
		lcm = new BigInteger[nodeCount];
//		gcd = new long[nodeCount];
//		resultLCM = new long[nodeCount];
//		resultGCD = new long[nodeCount];
		init(0, 0, period.length, period);
	}

	private void init(int root, int left, int right, int[] period) {
		if (left + 1 == right) {
//			gcd[root] = lcm[root] = period[left];
			lcm[root] = BigInteger.valueOf(period[left]);
//			lcm[root] = BigInteger.valueOf(period[left]);
		} else {
			int middle = (left + right) >> 1;
			init(2 * root + 1, left, middle, period);
			init(2 * root + 2, middle, right, period);
//			gcd[root] = IntegerUtils.gcd(gcd[2 * root + 1], gcd[2 * root + 2]);
//			lcm[root] = lcm[2 * root + 1] * lcm[2 * root + 2] % MOD * IntegerUtils.reverse(gcd[root], MOD) % MOD;
			lcm[root] = lcm[2 * root + 1].divide(lcm[2 * root + 1].gcd(lcm[2 * root + 2])).multiply(lcm[2 * root + 2]);
//			lcm[root] = lcm[2 * root + 1] * lcm[2 * root + 2] % MOD * IntegerUtils.reverse(IntegerUtils.gcd(lcm[2 * root + 1], lcm[2 * root + 2]), MOD) % MOD;
		}
	}

	long get(int from, int to) {
//		return get(0, from, to, 0, length);
		current = new ArrayList<BigInteger>();
		get(0, from, to, 0, length);
		Collections.sort(current, new ReverseComparator<BigInteger>());
		BigInteger result = current.get(0);
		for (int i = 1; i < current.size(); i++) {
			BigInteger next = current.get(i);
			BigInteger gcd = result.gcd(next);
			result = result.multiply(next.divide(gcd));
		}
		return result.mod(BigInteger.valueOf(MOD)).longValue();
//		return resultLCM[0];
	}

	private void get(int root, int from, int to, int left, int right) {
		if (from >= right || to <= left) {
//			resultLCM[root] = 1;
//			resultGCD[root] = 1;
//			return 1;
			return;
		}
		if (from <= left && right <= to) {
//			resultLCM[root] = lcm[root];
//			resultGCD[root] = gcd[root];
//			return;
			current.add(lcm[root]);
//			return lcm[root];
			return;
		}
		int middle = (left + right) >> 1;
		get(2 * root + 1, from, to, left, middle);
		get(2 * root + 2, from, to, middle, right);
//		resultGCD[root] = IntegerUtils.gcd(resultGCD[2 * root + 1], resultGCD[2 * root + 2]);
//		resultLCM[root] = resultLCM[2 * root + 1] * resultLCM[2 * root + 2] % MOD * IntegerUtils.reverse(resultGCD[root], MOD) % MOD;
//		return leftResult.divide(leftResult.gcd(rightResult)).multiply(rightResult);
//		return leftResult * rightResult % MOD * IntegerUtils.reverse(IntegerUtils.gcd(leftResult, rightResult), MOD) % MOD;
	}
}