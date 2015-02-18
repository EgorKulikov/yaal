package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BayanHealthBracelet {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine("Case #" + testNumber + ":");
		int temperature = in.readInt();
		int heartbeat = in.readInt();
		int movement = in.readInt();
		int time = in.readInt();
		if (movement >= 10) {
			out.printLine("NOTHING");
		} else if (temperature < 35 || heartbeat < 40) {
			out.printLine("EMERGENCY");
		} else if (heartbeat > 60) {
			out.printLine("NIGHTMARE");
		} else if (time > 8 * 3600) {
			out.printLine("WAKE-UP");
		} else {
			out.printLine("NOTHING");
		}
    }
}
