package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int[][] at = new int[100001][];
		IntList[] listAt = new IntList[100001];
		for (int i = 0; i < count; i++) {
			if (listAt[array[i]] == null) {
				listAt[array[i]] = new IntArrayList();
			}
			listAt[array[i]].add(i);
		}
		for (int i = 0; i <= 100000; i++) {
			if (listAt[i] != null) {
				at[i] = listAt[i].toArray();
			} else {
				at[i] = new int[0];
			}
		}
		long answer = 0;
		int[] set = new int[count];
		for (int i = 1; i <= 100000; i++) {
			int size = 0;
			for (int j = i; j <= 100000; j += i) {
				System.arraycopy(at[j], 0, set, size, at[j].length);
				size += at[j].length;
			}
			Arrays.sort(set, 0, size);
			int leftAt = -1;
			int rightAt = 0;
			for (int j : at[i]) {
				while (set[leftAt + 1] < j) leftAt++;
				while (rightAt < size && set[rightAt] <= j) rightAt++;
				answer += (long)array[leftAt == -1 ? j : set[leftAt]] * array[rightAt == size ? j : set[rightAt]];
			}
		}
		out.printLine(answer);
	}
}
