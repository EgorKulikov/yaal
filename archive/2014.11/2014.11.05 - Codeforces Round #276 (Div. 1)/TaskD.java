package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] charisma = IOUtils.readIntArray(in, count);
		IntList list = new IntArrayList();
		for (int i = 0; i < count; i++) {
			if (i < 2 || i >= count - 2 ||
				!(charisma[i] > charisma[i - 1] && charisma[i] > charisma[i - 2] && charisma[i] < charisma[i + 1] && charisma[i] < charisma[i + 2]) &&
				!(charisma[i] < charisma[i - 1] && charisma[i] < charisma[i - 2] && charisma[i] > charisma[i + 1] && charisma[i] > charisma[i + 2]))
			{
				list.add(charisma[i]);
			}
		}
		charisma = list.toArray();
		count = charisma.length;
		long[] answer = new long[count + 1];
		for (int i = 1; i <= count; i++) {
			for (int j = i - 1; j >= 0 && i - j <= 4; j--) {
				answer[i] = Math.max(answer[i], answer[j] + Math.abs(charisma[j] - charisma[i - 1]));
			}
		}
		out.printLine(answer[count]);
    }
}
