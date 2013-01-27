package net.egork;

import net.egork.collections.set.TreapSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NavigableSet;

public class TaskE {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int start = 0;
		long result = 0;
		for (int i = 1; i < count; i++) {
			if (array[i] > array[i - 1]) {
				Arrays.sort(array, start, i);
				start = i;
				result++;
			}
		}
		Arrays.sort(array, start, count);
		result++;
		NavigableSet<Integer> set = new TreapSet<Integer>();
		for (int i : array) {
			result += set.tailSet(i).size();
			set.add(i);
		}
		out.println(result);
	}
}
