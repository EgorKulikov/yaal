package old.approved.test1448218852;

import net.egork.utils.old.test.Test;
import java.util.Collection;
import net.egork.utils.Exit;
import net.egork.utils.old.io.StreamInputReader;
import java.io.*;
import net.egork.utils.old.io.old.InputReader;
import net.egork.utils.Solver;
public class Main {
	public static void main(String[] args) {
		net.egork.utils.old.io.old.InputReader in = new StreamInputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		run(in, out);
	}

	public static void run(InputReader in, PrintWriter out) {
		Solver solver = new Misinterpretation();
		int testCount = in.readInt();
		for (int i = 1; i <= testCount; i++)
			solver.solve(i, in, out);
		Exit.exit(in, out);
	}
}


class MainChecker {
	public static String check(InputReader input, net.egork.utils.old.io.old.InputReader expectedOutput, InputReader actualOutput) {
		return new MisinterpretationChecker().check(input, expectedOutput, actualOutput);
	}

	public static Collection<Test> generateTests() {
		return new MisinterpretationChecker().generateTests();
	}
}

