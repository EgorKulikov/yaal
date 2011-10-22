package approved.test324363384;

import net.egork.utils.test.Test;
import java.util.Collection;

import net.egork.utils.io.StreamInputReader;
import java.io.*;
import net.egork.utils.io.old.InputReader;
import net.egork.utils.Solver;
public class Main {
	public static void main(String[] args) {
		InputReader in = new StreamInputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		run(in, out);
	}

	public static void run(net.egork.utils.io.old.InputReader in, PrintWriter out) {
		Solver solver = new TaskA();
		int i = 1;
		while (true) {
			solver.solve(i++, in, out);
			if (in.isFinished())
				break;
		}
	}
}


class MainChecker {
	public static String check(net.egork.utils.io.old.InputReader input, InputReader expectedOutput, InputReader actualOutput) {
		return new TaskAChecker().check(input, expectedOutput, actualOutput);
	}

	public static Collection<Test> generateTests() {
		return new TaskAChecker().generateTests();
	}
}

