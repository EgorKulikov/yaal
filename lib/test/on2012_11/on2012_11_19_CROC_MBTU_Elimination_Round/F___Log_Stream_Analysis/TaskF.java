package on2012_11.on2012_11_19_CROC_MBTU_Elimination_Round.F___Log_Stream_Analysis;



import net.egork.datetime.Date;
import net.egork.datetime.Time;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.Queue;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int timeLimit = in.readInt();
		int count = in.readInt();
		Queue<Long> queue = new ArrayDeque<Long>();
		while (!in.isExhausted()) {
			String line = in.readLine();
			String date = line.substring(0, 10);
			String time = line.substring(11, 19);
			Date curDate = Date.parse(date, "YYYY-MM-DD");
			Time curTime = Time.parse(time, "HH:MM:SS");
			long total = (long)curDate.asInt() * 24 * 60 * 60 + curTime.totalSeconds();
			while (!queue.isEmpty() && total - queue.peek() >= timeLimit)
				queue.poll();
			if (queue.size() == count - 1) {
				out.printLine(line.substring(0, 19));
				return;
			}
			queue.add(total);
		}
		out.printLine(-1);
	}
}
