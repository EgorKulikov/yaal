package net.egork;

import net.egork.collections.Pair;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PDSNumber {
	static final long[][] c = IntegerUtils.generateBinomialCoefficients(20);

	List<Long> count = new ArrayList<Long>();
	List<Map<Integer, Map<Integer, Integer>>> cnt = new ArrayList<Map<Integer, Map<Integer, Integer>>>();

	{
		long total = 0;
		for (int i = 1; total < (int)1e9; i++) {
			Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
			map.put(-1, new HashMap<Integer, Integer>());
			map.get(-1).put(-1, 0);
			long go = go(new int[10], i, 0, map);
			count.add(go);
			total += go;
			cnt.add(map);
//			subsets.add(generateSubsets(current));
		}
	}

	private long go(int[] cur, int remaining, int step, Map<Integer, Map<Integer, Integer>> cnt) {
		if (step == 10) {
			if (remaining != 0)
				return 0;
			int sum = 0;
			long product2 = 1;
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < cur[i]; j++) {
					sum += i;
					product2 *= i;
				}
			}
			int product = (int) product2;
			int curCnt = (int) getFullCount(cur);
			if (cnt.containsKey(sum)) {
				if (cnt.get(sum).containsKey(product))
					cnt.get(sum).put(product, cnt.get(sum).get(product) + curCnt);
				else
					cnt.get(sum).put(product, curCnt);
				if (cnt.get(sum).containsKey(-1))
					cnt.get(sum).put(-1, cnt.get(sum).get(-1) + curCnt);
				else
					cnt.get(sum).put(-1, curCnt);
			} else {
				cnt.put(sum, new HashMap<Integer, Integer>());
				cnt.get(sum).put(product, curCnt);
				cnt.get(sum).put(-1, curCnt);
			}
			cnt.get(-1).put(-1, cnt.get(-1).get(-1) + curCnt);
			if (sum == 0 || product2 % sum != 0)
				return 0;
			return getCount(cur);
		}
		long result = 0;
		for (int i = 0; i <= remaining; i++) {
			result += go(cur, remaining - i, step + 1, cnt);
			cur[step]++;
		}
		cur[step] = 0;
		return result;
	}

	Map<Pair<Pair<Long, Integer>, Integer>, Long> result = new HashMap<Pair<Pair<Long, Integer>, Integer>, Long>();

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int index = in.readInt() - 1;
		if (index == -1)
			throw new UnknownError();
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < cnt.size(); i++) {
			if (index >= count.get(i))
				index -= count.get(i);
			else {
				int sum = 0;
				long product = 1;
				for (int j = 0; j <= i; j++) {
					for (int k = 0; k < 10; k++) {
						if (j == 0 && k == 0)
							continue;
						int curSum = sum + k;
						long curProduct = product * k;
						long current = 0;
						Pair<Pair<Long, Integer>, Integer> key = Pair.makePair(Pair.makePair(curProduct, curSum), j - i);
						if (result.containsKey(key))
							current = result.get(key);
						else {
							if (j == i) {
								if (curProduct % curSum == 0)
									current = 1;
							} else {
								if (curProduct == 0)
									current += cnt.get(i - j - 1).get(-1).get(-1);
								else {
									for (Map.Entry<Integer, Map<Integer, Integer>> entry : cnt.get(i - j - 1).entrySet()) {
										int fullSum = curSum + entry.getKey();
										if (entry.getKey() == -1)
											continue;
										if (curProduct % fullSum == 0)
											current += entry.getValue().get(-1);
										else {
											for (Map.Entry<Integer, Integer> entry2 : entry.getValue().entrySet()) {
												if (entry2.getKey() == -1)
													continue;
												long fullProduct = curProduct * entry2.getKey();
												if (fullProduct % fullSum == 0)
													current += entry2.getValue();
											}
										}
									}
								}
							}
							result.put(key, current);
						}
						if (index >= current)
							index -= current;
						else {
							sum = curSum;
							product = curProduct;
							answer.append(k);
							break;
						}
					}
				}
				out.printLine(answer);
				return;
			}
		}
	}

	long getCount(int[] cur) {
		long count = 0;
		int sum = 0;
		for (int i : cur)
			sum += i;
		for (int j = 1; j < 10; j++) {
			if (cur[j] == 0)
				continue;
			long cc = 1;
			int ss = sum - 1;
			cur[j]--;
			for (int i : cur) {
				cc *= c[ss][i];
				ss -= i;
			}
			cur[j]++;
			count += cc;
		}
		return count;
	}

	long getFullCount(int[] cur) {
		long count = 0;
		int sum = 0;
		for (int i : cur)
			sum += i;
		long cc = 1;
		int ss = sum;
		for (int i : cur) {
			cc *= c[ss][i];
			ss -= i;
		}
		count += cc;
		return count;
	}
}
