package on2014_11.on2014_11_05_Codeforces_Round__276__Div__1_.B___Maximum_Value;



import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		ArrayUtils.sort(numbers);
		numbers = ArrayUtils.unique(numbers);
		count = numbers.length;
		int[] current = new int[count];
		Heap heap = new Heap(new IntComparator() {
			@Override
			public int compare(int first, int second) {
				return current[first] - current[second];
			}
		}, count);
		int at = 1;
		int answer = 0;
		int[] first = new int[(int) (2e6 + 1)];
		int[] next = new int[count];
		Arrays.fill(first, -1);
		for (int i1 = 0; i1 < numbers.length; i1++) {
			int i = numbers[i1];
			while (at <= i) {
				for (int j = first[at]; j != -1; ) {
					heap.remove(j);
					int nxt = next[j];
					current[j] = at;
					next[j] = first[at + numbers[j]];
					first[at + numbers[j]] = j;
					heap.add(j);
					j = nxt;
				}
				at++;
			}
			if (!heap.isEmpty()) {
				answer = Math.max(answer, i - current[heap.peek()]);
			}
			current[i1] = i;
			heap.add(i1);
			next[i1] = first[i * 2];
			first[i * 2] = i1;
		}
		out.printLine(answer);
    }
}
