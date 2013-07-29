package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int[] temp = new int[count + 2];
		System.arraycopy(array, 0, temp, 1, count);
		temp[count + 1] = count + 1;
		array = temp;
		boolean[] processed = new boolean[count + 2];
		int[] left = new int[count + 2];
		int[] right = new int[count + 2];
		for (int i = 0; i < count + 2; i++) {
			left[i] = i - 1;
			right[i] = i + 1;
		}
		int[] interesting = new int[count];
		int size = count;
		for (int i = 0; i < count; i++)
			interesting[i] = i + 1;
		int answer = -1;
		while (size != 0) {
			int newSize = 0;
			answer++;
			for (int i = 0; i < size; i++) {
				if (array[interesting[i]] > array[right[interesting[i]]]) {
					int position = right[interesting[i]];
					left[right[position]] = left[position];
					right[left[position]] = right[position];
					processed[position] = true;
					if (!processed[interesting[i]])
						interesting[newSize++] = interesting[i];
				}
			}
			size = newSize;
		}
		out.printLine(answer);
    }
}
