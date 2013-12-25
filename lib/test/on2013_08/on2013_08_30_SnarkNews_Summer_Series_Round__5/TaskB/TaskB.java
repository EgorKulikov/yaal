package on2013_08.on2013_08_30_SnarkNews_Summer_Series_Round__5.TaskB;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int start = in.readInt() - 1;
		if (start == -1)
			throw new UnknownError();
		int delta = in.readInt();
		int count = in.readInt();
		int[][] sequence = new int[count][];
		int[][] time = new int[count][];
		int[] period = new int[count];
		for (int i = 0; i < count; i++) {
			int length = in.readInt();
			period[i] = in.readInt();
			sequence[i] = IOUtils.readIntArray(in, length);
			time[i] = IOUtils.readIntArray(in, length);
		}
		MiscUtils.decreaseByOne(sequence);
		long[] answer = new long[500];
		Arrays.fill(answer, Long.MAX_VALUE);
		answer[start] = 0;
		IntList[] route = new IntList[500];
		IntList[] index = new IntList[500];
		for (int i = 0; i < 500; i++) {
			route[i] = new IntArrayList();
			index[i] = new IntArrayList();
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < sequence[i].length; j++) {
				route[sequence[i][j]].add(i);
				index[sequence[i][j]].add(j);
			}
		}
		boolean[] processed = new boolean[500];
		while (true) {
			int current = -1;
			long min = Long.MAX_VALUE;
			for (int i = 0; i < 500; i++) {
				if (!processed[i] && answer[i] < min) {
					min = answer[i];
					current = i;
				}
			}
			if (current == 0) {
				out.printLine(answer[0]);
				return;
			}
			long curTime = answer[current] + delta;
			processed[current] = true;
			for (int i = 0; i < route[current].size(); i++) {
				int curRoute = route[current].get(i);
				int curIndex = index[current].get(i);
				long difference;
				if (curTime >= time[curRoute][curIndex])
					difference = (curTime - time[curRoute][curIndex] + period[curRoute] - 1) / period[curRoute];
				else
					difference = (curTime - time[curRoute][curIndex]) / period[curRoute];
				difference *= period[curRoute];
				for (int j = curIndex + 1; j < sequence[curRoute].length; j++) {
					int stop = sequence[curRoute][j];
					answer[stop] = Math.min(answer[stop], time[curRoute][j] + difference);
				}
			}
		}
    }
}
