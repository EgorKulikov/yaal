package on2013_06.on2013_06_12_ABBYY_Cup_3_0.B1___EKG__30_points_;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB1 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int index = in.readInt() - 1;
		int[] last = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(last);
		int[] next = new int[count];
		Arrays.fill(next, -1);
		for (int i = 0; i < count; i++) {
			if (last[i] != -1)
				next[last[i]] = i;
		}
		IntList sizes = new IntArrayList();
		int toAdd = 1;
		boolean[] processed = new boolean[count];
		for (int i = 0; i < count; i++) {
			if (processed[i])
				continue;
			int size = 1;
			processed[i] = true;
			boolean isSmart = i == index;
			int j = next[i];
			while (j != -1) {
				size++;
				isSmart |= j == index;
				processed[j] = true;
				j = next[j];
			}
			j = last[i];
			int head = i;
			while (j != -1) {
				head = j;
				size++;
				isSmart |= j == index;
				processed[j] = true;
				j = last[j];
			}
			if (isSmart) {
				j = head;
				while (j != index) {
					toAdd++;
					j = next[j];
				}
			} else
				sizes.add(size);
		}
		boolean[] can = new boolean[count + 1];
		can[0] = true;
		for (int i : sizes.toArray()) {
			for (int j = count - i; j >= 0; j--) {
				if (can[j])
					can[j + i] = true;
			}
		}
		for (int i = 0; i <= count; i++) {
			if (can[i])
				out.printLine(i + toAdd);
		}
    }
}
