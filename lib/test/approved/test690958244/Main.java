package approved.test690958244;

import net.egork.utils.test.Test;
import java.util.Collection;
import net.egork.utils.Exit;
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
		Solver solver = new TaskB();
		solver.solve(1, in, out);
		Exit.exit(in, out);
	}
}


class MainChecker {
	public static String check(InputReader input, net.egork.utils.io.old.InputReader expectedOutput, InputReader actualOutput) {
		return new TaskBChecker().check(input, expectedOutput, actualOutput);
	}

	public static Collection<Test> generateTests() {
		return new TaskBChecker().generateTests();
	}
}

