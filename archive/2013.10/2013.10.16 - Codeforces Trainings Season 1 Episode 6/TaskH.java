package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int width = in.readInt();
		int height = in.readInt();
		int count = in.readInt();
		int[] x0 = new int[count];
		int[] y0 = new int[count];
		int[] x1 = new int[count];
		int[] y1 = new int[count];
		IOUtils.readIntArrays(in, x0, y0, x1, y1);
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				if (Math.min(x1[i], x1[j]) > Math.max(x0[i], x0[j]) && Math.min(y1[i], y1[j]) > Math.max(y0[i], y0[j])) {
					out.printLine("NONDISJOINT");
					return;
				}
			}
		}
		for (int i = 0; i < count; i++) {
			if (x0[i] < 0 || y0[i] < 0 || x1[i] > width || y1[i] > height) {
				out.printLine("NONCONTAINED");
				return;
			}
		}
		long area = width * height;
		for (int i = 0; i < count; i++)
			area -= (x1[i] - x0[i]) * (y1[i] - y0[i]);
		if (area != 0)
			out.printLine("NONCOVERING");
		else
			out.printLine("OK");
    }
}
