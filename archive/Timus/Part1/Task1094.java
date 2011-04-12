package Timus.Part1;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class Task1094 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		char[] screen = new char[80];
		Arrays.fill(screen, ' ');
		int position = 0;
		while (true) {
			if (position < 0 || position >= 80)
				position = 0;
			int ch = in.read();
			if (ch == -1)
				break;
			if (ch == '\n' || ch == '\r')
				continue;
			if (ch == '<')
				position--;
			else if (ch == '>')
				position++;
			else {
				screen[position] = (char) ch;
				position++;
			}
		}
		out.println(new String(screen));
	}
}

