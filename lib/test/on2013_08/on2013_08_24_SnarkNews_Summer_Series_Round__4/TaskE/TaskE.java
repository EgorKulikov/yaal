package on2013_08.on2013_08_24_SnarkNews_Summer_Series_Round__4.TaskE;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		IntList list = new IntArrayList();
		while (true) {
			int n = in.readInt();
			if (n == 0)
				break;
			list.add(n);
		}
		int[] array = list.toArray();
		int[] last = new int[65536];
		Arrays.fill(last, -1);
		int start = -1;
		int length = 0;
		int curStart = 0;
		for (int i = 0; i < array.length; i++) {
			curStart = Math.max(curStart, last[array[i]] + 1);
			last[array[i]] = i;
			int curLength = i - curStart + 1;
			if (curLength > length) {
				start = curStart;
				length = curLength;
			}
		}
		for (int i = start; i < start + length; i++)
			out.printLine(array[i]);
    }
}
