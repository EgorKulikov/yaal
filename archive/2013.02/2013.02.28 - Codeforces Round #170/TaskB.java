package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Random;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int total = in.readInt();
		int convex = in.readInt();
		if (convex == 3 && total > 4) {
			out.printLine(-1);
			return;
		}
		if (convex == 4 && total == 8) {
			out.printLine("-51480551 -5504567\n" +
				"-66607043 8617794\n" +
				"80762066 82944960\n" +
				"50944376 -33812311\n" +
				"-42481699 -29106059\n" +
				"85025994 -48295314\n" +
				"35923563 8047876\n" +
				"-57439314 -46935024");
			return;
		}
		for (int i = 0; i < convex; i++)
			out.printLine(Math.round(1e8 * Math.cos(2 * Math.PI / convex * i)), Math.round(1e8 * Math.sin(2 * Math.PI / convex * i)));
		if (convex != total) {
			double delta = new Random(239).nextDouble();
			for (int i = 0; i < total - convex; i++)
				out.printLine(Math.round(1e4 * Math.cos(2 * Math.PI / (total - convex) * i + delta)), Math.round(1e4 * Math.sin(2 * Math.PI / (total - convex) * i + delta)));
		}
    }
}
