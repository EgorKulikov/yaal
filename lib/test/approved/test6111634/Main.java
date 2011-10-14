package approved.test6111634;

import net.egork.utils.test.Test;
import java.util.Collection;
import net.egork.utils.Exit;
import net.egork.utils.io.StreamInputReader;
import java.io.*;
import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;
public class Main {
	public static void main(String[] args) {
		try {
			System.setIn(new FileInputStream("input.txt"));
			System.setOut(new PrintStream(new FileOutputStream("output.txt")));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		InputReader in = new StreamInputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		run(in, out);
	}

	public static void run(InputReader in, PrintWriter out) {
		Solver solver = new TaskH();
		solver.solve(1, in, out);
		Exit.exit(in, out);
	}
}


class MainChecker {
	public static String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {
		return new TaskHChecker().check(input, expectedOutput, actualOutput);
	}

	public static Collection<Test> generateTests() {
		return new TaskHChecker().generateTests();
	}
}

