package old.approved.test1117144249;

import net.egork.utils.old.test.Test;
import java.util.Collection;
import net.egork.utils.Exit;
import net.egork.utils.old.io.StreamInputReader;
import java.io.*;

import net.egork.utils.Solver;
public class Main {
	public static void main(String[] args) {
		net.egork.utils.old.io.old.InputReader in = new StreamInputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		run(in, out);
	}

	public static void run(net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		Solver solver = new TaskD();
		solver.solve(1, in, out);
		Exit.exit(in, out);
	}
}


class MainChecker {
	public static String check(
		net.egork.utils.old.io.old.InputReader input, net.egork.utils.old.io.old.InputReader expectedOutput, net.egork.utils.old.io.old.InputReader actualOutput) {
		return new TaskDChecker().check(input, expectedOutput, actualOutput);
	}

	public static Collection<Test> generateTests() {
		return new TaskDChecker().generateTests();
	}
}

