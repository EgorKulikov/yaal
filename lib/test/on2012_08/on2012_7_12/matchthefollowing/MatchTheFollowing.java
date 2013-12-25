package on2012_08.on2012_7_12.matchthefollowing;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class MatchTheFollowing {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] left = new int[count];
		final int[] right = new int[count];
		IOUtils.readIntArrays(in, left, right);
		int[] order = ArrayUtils.order(left);
		NavigableSet<Integer> set = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				int value = right[o1] - right[o2];
				if (value != 0)
					return value;
				return o1 - o2;
			}
		});
		long answer = 0;
		for (int i : order) {
			answer += set.tailSet(i).size();
			set.add(i);
		}
		out.printLine(answer);
	}
}
