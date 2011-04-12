package Timus.Part1;

import net.egork.datetime.DateTimeUtils;
import net.egork.datetime.Time;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1070 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		Time outboundDeparture = Time.parse(in.readString(), "hh.mm");
		Time outboundArrival = Time.parse(in.readString(), "hh.mm");
		Time inboundDeparture = Time.parse(in.readString(), "hh.mm");
		Time inboundArrival = Time.parse(in.readString(), "hh.mm");
		Time outboundDifference = DateTimeUtils.minimalTimePassed(outboundDeparture, outboundArrival);
		Time inboundDifference = DateTimeUtils.minimalTimePassed(inboundDeparture, inboundArrival);
		int timeDifferenceInMinutes = Math.abs(outboundDifference.totalMinutes() - inboundDifference.totalMinutes()) / 2;
		int result = ((timeDifferenceInMinutes + 30) / 60);
		result %= 12;
		if (result >= 6)
			result = 12 - result;
		out.println(result);
	}
}

