package on2013_11.on2013_11_26_Codeforces_Round__215__Div__1_.B___Sereja_ans_Anagrams;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.map.Counter;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	int nonZero;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int sampleCount = in.readInt();
		int step = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int[] sample = IOUtils.readIntArray(in, sampleCount);
		IntList answer = new IntArrayList();
		int bigStep = sampleCount * step;
		for (int i = 0; i < step; i++) {
			if (i + (long)step * (sampleCount - 1) >= count)
				continue;
			Counter<Integer> counter = new Counter<Integer>() {
				@Override
				public long add(Integer key) {
					long result = super.add(key);
					if (result == 1)
						nonZero++;
					else if (result == 0)
						nonZero--;
					return result;
				}

				@Override
				public void add(Integer key, long delta) {
					long current = get(key);
					put(key, current + delta);
					if (current + delta == 0)
						nonZero--;
					else if (current == 0)
						nonZero++;
				}
			};
			nonZero = 0;
			for (int j : sample)
				counter.add(j);
			int k;
			for (k = i; k < count; k += step) {
				if (k - bigStep >= 0 && nonZero == 0) {
					answer.add(k - bigStep + 1);
				}
				if (k - bigStep >= 0)
					counter.add(array[k - bigStep]);
				counter.add(array[k], -1);
			}
			if (nonZero == 0)
				answer.add(k - bigStep + 1);
		}
		answer.inPlaceSort();
		out.printLine(answer.size());
		out.printLine(answer);
    }
}
