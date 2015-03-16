package net.egork;

import net.egork.collections.intcollection.IntPair;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FoxConnection3 {
	long[] result;

    public long minimalSteps(int[] x, int[] y) {
		IntPair[] array = new IntPair[x.length];
		array[0] = new IntPair(0, 0);
		result = new long[1 << x.length];
		Set<IntPair> in = new HashSet<>();
		in.add(array[0]);
		long answer = go(x, y, array, in, new HashSet<>());
		return answer;
	}

	private long go(int[] x, int[] y, IntPair[] array, Set<IntPair> in, Set<IntPair> out) {
		long answer = Long.MAX_VALUE;
		if (in.size() == x.length) {
			int maxX = 0;
			int minX = 0;
			int maxY = 0;
			int minY = 0;
			for (int i = 0; i < x.length; i++) {
				minX = Math.min(minX, array[i].first);
				minY = Math.min(minY, array[i].second);
				maxX = Math.max(maxX, array[i].first);
				maxY = Math.max(maxY, array[i].second);
			}
			for (int i = 0; i < x.length; i++) {
				for (int j = 0; j < x.length; j++) {
					for (int k = minX; k <= maxX; k++) {
						for (int l = minY; l <= maxY; l++) {
							long dx = x[i] - k;
							long dy = y[j] - l;
							result[0] = 0;
							for (int m = 1; m < result.length; m++) {
								result[m] = Long.MAX_VALUE;
								int index = Integer.bitCount(m) - 1;
								for (int n = 0; n < x.length; n++) {
									if ((m >> n & 1) != 0) {
										result[m] = Math.min(result[m], result[m - (1 << n)] +
											Math.abs(dx + array[index].first - x[n]) + Math.abs(dy + array[index].second - y[n]));
									}
								}
							}
							answer = Math.min(answer, result[result.length - 1]);
						}
					}
				}
			}
			return answer;
		}
		Set<IntPair> curOut = new HashSet<>();
		for (int i = 0; i < in.size(); i++) {
			for (int j = 0; j < 4; j++) {
				IntPair current = new IntPair(array[i].first + MiscUtils.DX4[j],
					array[i].second + MiscUtils.DY4[j]);
				if (in.contains(current) || out.contains(current) || current.first < 0 || current.first == 0 && current.second < 0) {
					continue;
				}
				array[in.size()] = current;
				in.add(current);
				answer = Math.min(answer, go(x, y, array, in, out));
				in.remove(current);
				curOut.add(current);
				out.add(current);
			}
		}
		out.removeAll(curOut);
		return answer;
	}
}
