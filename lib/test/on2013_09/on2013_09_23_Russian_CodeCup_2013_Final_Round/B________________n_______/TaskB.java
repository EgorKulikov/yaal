package on2013_09.on2013_09_23_Russian_CodeCup_2013_Final_Round.B________________n_______;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] height = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(height);
		int[] index = new int[count];
		Arrays.fill(index, -1);
		IntList answer = new IntArrayList();
		int current = 0;
		for (int i = 0; ; i++) {
			answer.add(current + 1);
			index[current] = i;
			current = height[current];
			if (index[current] != -1) {
				answer = answer.subList(index[current], i + 1);
				break;
			}
		}
		answer.inPlaceSort();
		out.printLine(answer.size());
		out.printLine(answer);
    }
}
