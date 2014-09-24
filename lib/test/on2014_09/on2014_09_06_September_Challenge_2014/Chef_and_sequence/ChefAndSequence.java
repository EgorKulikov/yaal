package on2014_09.on2014_09_06_September_Challenge_2014.Chef_and_sequence;



import net.egork.collections.intcollection.IntPair;
import net.egork.collections.map.Counter;
import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;
import java.util.Set;

public class ChefAndSequence {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int change = in.readInt();
		int[] array = IOUtils.readIntArray(in, size);
		IntPair answer = null;
		if (size > 100) {
			Counter<IntPair> variants = new Counter<>();
			for (int i = 0; i < size - 1; i++) {
				int delta = array[i + 1] - array[i];
				variants.add(new IntPair(array[i] - delta * i, delta));
			}
			long max = 0;
			for (Map.Entry<IntPair, Long> entry : variants.entrySet()) {
				if (entry.getValue() > max) {
					answer = entry.getKey();
					max = entry.getValue();
				}
			}
		} else {
			Set<IntPair> variants = new EHashSet<>();
			for (int i = 0; i < size - 1; i++) {
				int delta = array[i + 1] - array[i];
				variants.add(new IntPair(array[i] - delta * i, delta));
			}
			for (IntPair variant : variants) {
				if (answer != null && answer.compareTo(variant) <= 0) {
					continue;
				}
				int errors = 0;
				for (int i = 0; i < size; i++) {
					long predicted = variant.first + (long)i * variant.second;
					if (predicted != array[i]) {
						errors++;
					}
				}
				if (errors <= change) {
					answer = variant;
				}
			}
		}
		long[] result = new long[size];
		for (int i = 0; i < size; i++) {
			result[i] = answer.first + (long)i * answer.second;
		}
		out.printLine(result);
    }
}
