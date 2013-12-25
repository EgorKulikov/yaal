package on2011_10.on2011_9_29.taskh;



import net.egork.datetime.Time;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class TaskH {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int[][] delays = IOUtils.readIntTable(in, 2, 2);
		int takeOffCount = in.readInt();
		Time[] takeOffTimes = new Time[takeOffCount];
		for (int i = 0; i < takeOffCount; i++)
			takeOffTimes[i] = Time.parse(in.readString(), "hh:mm:ss");
		int landingCount = in.readInt();
		Time[] landingTimes = new Time[landingCount];
		for (int i = 0; i < landingCount; i++)
			landingTimes[i] = Time.parse(in.readString(), "hh:mm:ss");
		Queue<Integer> takeOffQueue = new ArrayDeque<Integer>();
		Queue<Integer> landingQueue = new ArrayDeque<Integer>();
		Time lastTakeOff = new Time(0, 0, 0);
		Time lastLanding = new Time(0, 0, 0);
		int processed = 0;
		for (Time currentTime = new Time(6, 0, 0); processed != takeOffCount + landingCount; currentTime = currentTime.advance(1)) {
			for (int i = 0; i < takeOffCount; i++) {
				if (currentTime.equals(takeOffTimes[i]))
					takeOffQueue.add(i);
			}
			for (int i = 0; i < landingCount; i++) {
				if (currentTime.equals(landingTimes[i]))
					landingQueue.add(i);
			}
			if (!landingQueue.isEmpty() &&
				lastTakeOff.advance(delays[0][1]).compareTo(currentTime) <= 0 &&
				lastLanding.advance(delays[1][1]).compareTo(currentTime) <= 0)
			{
				landingTimes[landingQueue.poll()] = currentTime;
				lastLanding = currentTime;
				processed++;
			} else if (landingQueue.isEmpty() && !takeOffQueue.isEmpty() &&
				lastTakeOff.advance(delays[0][0]).compareTo(currentTime) <= 0 &&
				lastLanding.advance(delays[1][0]).compareTo(currentTime) <= 0)
			{
				takeOffTimes[takeOffQueue.poll()] = currentTime;
				lastTakeOff = currentTime;
				processed++;
			}
		}
		for (Time takeOffTime : takeOffTimes)
			out.println(takeOffTime);
		for (Time landingTime : landingTimes)
			out.println(landingTime);
	}
}
