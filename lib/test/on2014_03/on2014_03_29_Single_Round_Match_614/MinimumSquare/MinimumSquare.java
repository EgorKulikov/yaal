package on2014_03.on2014_03_29_Single_Round_Match_614.MinimumSquare;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;

public class MinimumSquare {
    public long minArea(int[] x, int[] y, int K) {
		long answer = Integer.MAX_VALUE;
		for (int i : x) {
			for (int j : y) {
				IntList delta = new IntArrayList();
				for (int k = 0; k < x.length; k++) {
					if (x[k] >= i && y[k] >= j)
						delta.add(Math.max(x[k] - i, y[k] - j));
				}
				if (delta.size() < K)
					continue;
				delta.inPlaceSort();
				answer = Math.min(answer, delta.get(K - 1));
			}
		}
		return (answer + 2) * (answer + 2);
    }
}
