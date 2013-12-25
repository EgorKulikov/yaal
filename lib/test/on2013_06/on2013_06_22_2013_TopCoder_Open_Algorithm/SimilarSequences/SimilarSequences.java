package on2013_06.on2013_06_22_2013_TopCoder_Open_Algorithm.SimilarSequences;



import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntSet;
import net.egork.collections.set.EHashSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimilarSequences {
	static final int MOD = (int) (1e9 + 9);

    public int count(int[] seq, int bound) {
		Set<List<Integer>> set = new EHashSet<List<Integer>>();
		int count = seq.length;
		Set<List<Integer>> special = new EHashSet<List<Integer>>();
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				List<Integer> current = new ArrayList<Integer>(count);
				for (int k = 0; k < count; k++) {
					if (current.size() == j)
						current.add(-1);
					if (k != i)
						current.add(seq[k]);
				}
				if (current.size() == j)
					current.add(-1);
				if (!set.contains(current)) {
					set.add(current);
					for (int k : seq) {
						List<Integer> temp = new ArrayList<Integer>(current);
						temp.set(j, k);
						special.add(temp);
					}
				}
			}
		}
		IntSet seqSet = new IntHashSet();
		for (int i : seq)
			seqSet.add(i);
		long answer = (long)(bound - seqSet.size()) * set.size() + special.size();
		answer %= MOD;
		return (int) answer;
    }
}
