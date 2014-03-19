package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long length = in.readInt();
		if (length == 0) {
			out.printLine(1);
			return;
		}
//		if (length == 1) {
//			out.printLine(4);
//			return;
//		}
//		if (length == 2) {
//			out.printLine(8);
//			return;
//		}
//		if (length == 3) {
//			out.printLine(16);
//			return;
//		}
		long ray = Math.round(Math.floor(length / Math.sqrt(2)));
//		long delta = Math.round(Math.floor(Math.sqrt(length * length - (ray - 1) * (ray - 1))));
//		while (delta * delta + (ray - 1) * (ray - 1) > length * length)
//			delta--;
//		while ((delta + 1) * (delta + 1) + (ray - 1) * (ray - 1) <= length * length)
//			delta++;
		long delta2 = Math.round(Math.floor(Math.sqrt(length * length - ray * ray)));
		while (delta2 * delta2 + ray * ray > length * length)
			delta2--;
		while ((delta2 + 1) * (delta2 + 1) + ray * ray <= length * length)
			delta2++;
		long answer = ray * 8;
		if (delta2 != ray)
			answer += 4;
		out.printLine(answer);
    }
}
