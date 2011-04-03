package net.egork.y2011.m4.d3.regionalcontestfrommexico;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;
import java.util.InputMismatchException;

public class CodeNumber implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		in.readLine();
		boolean emptyLine = false;
		try {
			//noinspection InfiniteLoopStatement
			while (true) {
				String line = in.readLine(false);
				if ("".equals(line))
					emptyLine = true;
				else {
					if (emptyLine)
						out.println();
					out.println(line.replace('0', 'O').replace('1', 'I').replace('2', 'Z').replace('3', 'E').replace('4', 'A').
						replace('5', 'S').replace('6', 'G').replace('7', 'T').replace('8', 'B').replace('9', 'P'));
				}
			}
		} catch (InputMismatchException ignored) {
		}
	}
}

