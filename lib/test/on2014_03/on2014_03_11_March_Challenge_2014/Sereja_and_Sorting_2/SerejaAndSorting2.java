package on2014_03.on2014_03_11_March_Challenge_2014.Sereja_and_Sorting_2;



import net.egork.collections.intcollection.IntPair;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class SerejaAndSorting2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		List<IntPair> result = new ArrayList<IntPair>();
		int from = 0;
		int to = count - 1;
		while (from < to) {
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (int i = from; i <= to; i++) {
				min = Math.min(min, array[i]);
				max = Math.max(max, array[i]);
			}
			if (min == max)
				break;
			if (array[from] == max && array[to] == min) {
				for (int i = from, j = to; i < j; i++, j--) {
					int t = array[i];
					array[i] = array[j];
					array[j] = t;
				}
				result.add(new IntPair(from + 1, to + 1));
				from++;
				to--;
				continue;
			}
			int firstMin = -1;
			for (int i = from; i <= to; i++) {
				if (array[i] == min) {
					firstMin = i;
					break;
				}
			}
			int lastMax = -1;
			for (int i = to; i >= from; i--) {
				if (array[i] == max) {
					lastMax = i;
					break;
				}
			}
			if (firstMin - from < to - lastMax) {
				if (from != firstMin) {
					for (int i = from, j = firstMin; i < j; i++, j--) {
						int t = array[i];
						array[i] = array[j];
						array[j] = t;
					}
					result.add(new IntPair(from + 1, firstMin + 1));
				}
				from++;
			} else {
				if (to != lastMax) {
					for (int i = lastMax, j = to; i < j; i++, j--) {
						int t = array[i];
						array[i] = array[j];
						array[j] = t;
					}
					result.add(new IntPair(lastMax + 1, to + 1));
				}
				to--;
			}
		}
		out.printLine(result.size());
		for (IntPair pair : result)
			out.printLine(pair.first, pair.second);
    }
}
