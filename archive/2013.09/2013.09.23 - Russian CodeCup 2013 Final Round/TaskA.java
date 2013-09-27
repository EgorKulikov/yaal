package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long total = in.readLong();
		long index = in.readLong();
		long result = solve(total, index);
		out.printLine(result <= 0 ? -1 : result);
    }

	private long solve(long total, long index) {
		if (total == 0)
			return 0;
		if ((total & 1) == 1) {
			long count = total >> 1;
			if (index > count)
				return -count;
			return index << 1;
		}
		long result = solve(total >> 2, index);
		if (result > 0)
			return result;
		index += result;
		long allWin = (total >> 1) - (total >> 2);
		if (index <= allWin)
			return index + (total >> 2);
		index -= allWin;
		if ((index <= (total >> 2))) {
			long answer = (total >> 1) + (index << 1);
			if ((answer & 1) == 0)
				answer--;
			return answer;
		}
		return -(-result + allWin + (total >> 2));
	}
}
