package April2011.UVaHugeEasyContestII;

import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskF implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		char[] program = in.readString().toCharArray();
		int[] memory = new int[100];
		int pointer = 0;
		for (char command : program) {
			if (command == '>') {
				pointer++;
				if (pointer == 100)
					pointer = 0;
			} else if (command == '<') {
				pointer--;
				if (pointer == -1)
					pointer = 99;
			} else if (command == '+')
				memory[pointer] = (memory[pointer] + 1) & 255;
			else if (command == '-')
				memory[pointer] = (memory[pointer] - 1) & 255;
		}
		out.print("Case " + testNumber + ":");
		for (int cell : memory)
			out.print(" " + Integer.toHexString(cell / 16).toUpperCase() + Integer.toHexString(cell % 16).toUpperCase());
		out.println();
	}
}

