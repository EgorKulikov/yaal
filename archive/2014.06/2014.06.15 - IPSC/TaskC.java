package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.intcollection.IntSet;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		IntList answer = new IntArrayList();
		IntSet was = new IntHashSet();
		for (int i : array) {
			if (!was.contains(i)) {
				answer.add(i);
				was.add(i);
			}
		}
		out.printLine(answer);
//		IntList list = new IntArray(array);
//		for (int i = 0; i < count; i++) {
//			int[] z = zAlgorithm(list.subList(i, count));
//			for (int j = 1; j < z.length; j++) {
//				if (z[j] >= j) {
//					out.print(list.subList(0, i));
//					out.print(' ');
//					out.printLine(list.subList(i + j, count));
//					return;
//				}
//			}
//		}
//		throw new RuntimeException();
    }

	public static int[] zAlgorithm(IntList s) {
		int length = s.size();
		int[] z = new int[length];
		int left = 0, right = 0;
		for (int i = 1; i < length; i++) {
			if (i > right) {
				int j;
				//noinspection StatementWithEmptyBody
				for (j = 0; i + j < length && s.get(i + j) == s.get(j); j++);
				z[i] = j;
				left = i;
				right = i + j - 1;
			} else if (z[i - left] < right - i + 1)
				z[i] = z[i - left];
			else {
				int j;
				//noinspection StatementWithEmptyBody
				for (j = 1; right + j < length && s.get(right + j) == s.get(right - i + j); j++);
				z[i] = right - i + j;
				left = i;
				right = right + j - 1;
			}
		}
		return z;
	}

}
