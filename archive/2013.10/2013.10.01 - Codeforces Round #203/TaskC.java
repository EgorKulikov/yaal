package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		int[] distance = new int[count];
		for (int i = 0; i < count; i++)
			distance[i] = Math.abs(x[i]) + Math.abs(y[i]);
		ArrayUtils.orderBy(distance, x, y);
		int operations = 4 * count;
		for (int i = 0; i < count; i++) {
			if (x[i] != 0 && y[i] != 0)
				operations += 2;
		}
		out.printLine(operations);
		for (int i = 0; i < count; i++) {
			if (x[i] > 0)
				out.printLine(1, x[i], 'R');
			else if (x[i] < 0)
				out.printLine(1, -x[i], 'L');
			if (y[i] > 0)
				out.printLine(1, y[i], 'U');
			else if (y[i] < 0)
				out.printLine(1, -y[i], 'D');
			out.printLine(2);
			if (x[i] > 0)
				out.printLine(1, x[i], 'L');
			else if (x[i] < 0)
				out.printLine(1, -x[i], 'R');
			if (y[i] > 0)
				out.printLine(1, y[i], 'D');
			else if (y[i] < 0)
				out.printLine(1, -y[i], 'U');
			out.printLine(3);
		}
    }
}
