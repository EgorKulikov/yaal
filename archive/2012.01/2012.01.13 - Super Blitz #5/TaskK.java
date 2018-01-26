package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskK {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int daysRented = in.readInt();
		int freeDay = in.readInt();
		int price = in.readInt();
		out.printLine((daysRented - daysRented / freeDay) * price);
	}
}
