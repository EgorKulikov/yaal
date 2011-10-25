package old.approved.test1321593129;

import net.egork.utils.old.test.Test;
import java.util.Collection;

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
		Solver solver = new APrimeConjecture();
		int i = 1;
		while (true) {
			solver.solve(i++, in, out);
			if (in.isFinished())
				break;
		}
	}
}


class MainChecker {
	public static String check(
		net.egork.utils.old.io.old.InputReader input, net.egork.utils.old.io.old.InputReader expectedOutput, net.egork.utils.old.io.old.InputReader actualOutput) {
		return new APrimeConjectureChecker().check(input, expectedOutput, actualOutput);
	}

	public static Collection<Test> generateTests() {
		return new APrimeConjectureChecker().generateTests();
	}
}

