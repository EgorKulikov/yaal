package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	private String[] rows = {"`1234567890-=", "QWERTYUIOP[]\\", "ASDFGHJKL;'", "ZXCVBNM,./", "  ", "\n\n", "\r\r"};
	private char[] map = new char[256];

	{
		for (String row : rows) {
			for (int i = 1; i < row.length(); i++)
				map[row.charAt(i)] = row.charAt(i - 1);
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int c;
		while ((c = in.read()) != -1)
			out.print(map[c]);
	}
}
