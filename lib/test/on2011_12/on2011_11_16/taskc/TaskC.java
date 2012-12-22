package on2011_12.on2011_11_16.taskc;



import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Pair<Integer, Integer>[] events = IOUtils.readIntPairArray(in, count);
		Arrays.sort(events);
		int end = 0;
		int answer = 0;
		for (Pair<Integer, Integer> event : events) {
			if (event.second < end)
				answer++;
			else
				end = event.second;
		}
		out.printLine(answer);
	}
}
