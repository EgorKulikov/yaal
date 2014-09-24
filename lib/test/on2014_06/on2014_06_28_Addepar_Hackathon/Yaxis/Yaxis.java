package on2014_06.on2014_06_28_Addepar_Hackathon.Yaxis;



import net.egork.collections.set.TreapSet;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;
import java.util.NavigableSet;

public class Yaxis {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		final int[] m = new int[count];
		final int[] b = new int[count];
		IOUtils.readIntArrays(in, m, b);
		ArrayUtils.orderBy(b, m);
		NavigableSet<Integer> down = new TreapSet<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return m[o1] - m[o2];
			}
		});
		NavigableSet<Integer> up = new TreapSet<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return m[o1] - m[o2];
			}
		});
		for (int i = 0; i < count; i++) {
			up.add(i);
		}
		long answer = 0;
		for (int i = 0; i < count; i++) {
			up.remove(i);
			int one = down.tailSet(i, false).size() + up.headSet(i, false).size();
			answer += (long)one * (count - 1 - one);
			down.add(i);
		}
		answer /= 2;
		out.printLine(answer);
	}
}
