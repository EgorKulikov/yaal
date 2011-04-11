package net.egork.y2011.m4.d9.hugeeasycontest;

import net.egork.datetime.Time;
import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskH implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int busCount = in.readInt();
		int startTime = Time.parse(in.readString(), "hh:mm").totalMinutes();
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < busCount; i++) {
			int busTime = Time.parse(in.readString(), "hh:mm").totalMinutes();
			int travelTime = in.readInt();
			if (busTime >= startTime)
				answer = Math.min(answer, busTime - startTime + travelTime);
			else
				answer = Math.min(answer, busTime - startTime + travelTime + 24 * 60);
		}
		out.println("Case " + testNumber + ": " + answer);
	}
}

