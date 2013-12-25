package on2013_12.on2013_12_04_Single_Round_Match_599.FindPolygons;



import net.egork.collections.intcollection.IntPair;
import net.egork.numbers.IntegerUtils;

import java.util.ArrayList;
import java.util.List;

public class FindPolygons {
    public double minimumPolygon(int L) {
		if (L % 2 == 1 || L == 2)
			return -1;
		int limit = L / 2;
		List<IntPair>[] variants = new List[limit];
		for (int i = 0; i < limit; i++)
			variants[i] = new ArrayList<IntPair>();
		for (int i = 1; i < limit; i++) {
			variants[i].add(new IntPair(i, 0));
			variants[i].add(new IntPair(0, i));
		}
		for (int i = 2; i * i < limit; i++) {
			for (int j = 1; i * i + j * j < limit; j++) {
				if (IntegerUtils.gcd(i, j) != 1)
					continue;
				for (int k = 1; k * (i * i + j * j) < limit; k++) {
					int a = (i * i - j * j) * k;
					int b = 2 * i * j * k;
					int c = (i * i + j * j) * k;
					variants[c].add(new IntPair(a, b));
					variants[c].add(new IntPair(b, a));
				}
			}
		}
		int answer = Integer.MAX_VALUE;
		for (int i = 1; 3 * i <= L; i++) {
			for (int j = i; i + 2 * j <= L; j++) {
				int k = L - i - j;
				if (k >= i + j || k - i >= answer)
					continue;
				for (IntPair first : variants[i]) {
					for (IntPair second : variants[j]) {
						for (IntPair third : variants[k]) {
							if (Math.max(Math.max(first.first, second.first), third.first) * 2 == first.first + second.first + third.first &&
								Math.max(Math.max(first.second, second.second), third.second) * 2 == first.second + second.second + third.second)
							{
								answer = k - i;
								break;
							}
						}
					}
				}
			}
		}
		if (answer != Integer.MAX_VALUE)
			return answer;
		return L % 4 / 2;
    }
}
