package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int types = in.readInt();
		int power = in.readInt();
		if (types >= 4 && (rowCount >= power - 1 || columnCount >= power - 1)) {
			out.printLine("YES");
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					int ii = i % (2 * power - 2) / (power - 1);
					int jj = j % (2 * power - 2) / (power - 1);
					if (ii == 0 && jj == 0)
						out.print('A');
					else if (ii == 0 && jj == 1)
						out.print('B');
					else if (ii == 1 && jj == 1)
						out.print('C');
					else if (ii == 1 && jj == 0)
						out.print('D');
				}
				out.printLine();
			}
			return;
		}
		if (power > 2 && types >= 2 && rowCount >= power - 1) {
			out.printLine("YES");
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					int ii = i % (2 * power - 2) / (power - 1);
					int jj = j % 2;
					if ((ii == 1) ^ (jj == 1))
						out.print('A');
					else
						out.print('B');
				}
				out.printLine();
			}
			return;
		}
		if (power > 2 && types >= 2 && columnCount >= power - 1) {
			out.printLine("YES");
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					int ii = i % 2;
					int jj = j % (2 * power - 2) / (power - 1);
					if ((ii == 1) ^ (jj == 1))
						out.print('A');
					else
						out.print('B');
				}
				out.printLine();
			}
			return;
		}
		if (Math.max(rowCount, columnCount) == power - 1) {
			out.printLine("YES");
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++)
					out.print('A');
				out.printLine();
			}
			return;
		}
		out.printLine("NO");
	}
}
