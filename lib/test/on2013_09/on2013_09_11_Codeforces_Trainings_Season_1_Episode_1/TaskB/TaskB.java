package on2013_09.on2013_09_11_Codeforces_Trainings_Season_1_Episode_1.TaskB;



import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		int[] bases = IOUtils.readIntArray(in, count);
		int start = in.readInt();
		int[] current = new int[count];
		final long[] value = new long[count];
		Arrays.fill(value, 1);
		Arrays.fill(current, 1);
		Heap heap = new Heap(new IntComparator() {
			public int compare(int first, int second) {
				return IntegerUtils.longCompare(value[first], value[second]);
			}
		}, count);
		long[] number = new long[5];
		IntList[] answer = new IntArrayList[5];
		int index = 0;
		for (int i = 0; i < count; i++)
			heap.add(i);
		while (index < 5) {
			int base = heap.poll();
			if (value[base] == value[heap.peek()]) {
				if (value[base] >= start) {
					number[index] = value[base];
					answer[index] = new IntArrayList();
					answer[index].add(bases[base]);
					while (value[base] == value[heap.peek()]) {
						int next = heap.poll();
						answer[index].add(bases[next]);
						current[next] += bases[next] - 2;
						value[next] += current[next];
						heap.add(next);
					}
					index++;
				} else {
					while (value[base] == value[heap.peek()]) {
						int next = heap.poll();
						current[next] += bases[next] - 2;
						value[next] += current[next];
						heap.add(next);
					}
				}
			}
			current[base] += bases[base] - 2;
			value[base] += current[base];
			heap.add(base);
		}
		if (testNumber != 1)
			out.printLine();
		for (int i = 0; i < 5; i++) {
			out.print(number[i] + ":");
			answer[i].inPlaceSort();
			out.printLine(answer[i]);
		}
    }
}
