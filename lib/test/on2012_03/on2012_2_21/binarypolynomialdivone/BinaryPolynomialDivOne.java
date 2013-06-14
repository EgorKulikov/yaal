package on2012_03.on2012_2_21.binarypolynomialdivone;



import net.egork.collections.Pair;

import java.util.HashMap;
import java.util.Map;

public class BinaryPolynomialDivOne {
	Map<Pair<Long, Integer>, Integer> answer = new HashMap<Pair<Long, Integer>, Integer>();

	public int findCoefficient(int[] a, long m, long k) {
		return go(a, m, k, 0);
	}

	private int go(int[] a, long m, long k, int step) {
		if (m == 0)
			return k == 0 ? 1 : 0;
		long ratio = Long.lowestOneBit(m);
		if (((ratio - 1) & k) != 0)
			return 0;
		int addSteps = Long.bitCount(ratio - 1);
		m >>= addSteps;
		k >>= addSteps;
		step += addSteps;
		Pair<Long, Integer> key = Pair.makePair(k, step);
		if (answer.containsKey(key))
			return answer.get(key);
		int result = 0;
		for (int i = (int) (k & 1); i <= k && i < a.length; i += 2) {
			if (a[i] == 1)
				result += go(a, m - 1, k - i, step);
		}
		result &= 1;
		answer.put(key, result);
		return result;
	}


}

