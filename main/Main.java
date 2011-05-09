import net.egork.utils.Exit;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.StreamInputReader;
import net.egork.utils.test.Test;

import java.io.PrintWriter;
import java.util.Collection;
public class Main {
	public static void main(String[] args) {
		InputReader in = new StreamInputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		run(in, out);
	}

	public static void run(InputReader in, PrintWriter out) {
		Solver solver = new BickeringCooks();
		int testCount = in.readInt();
		for (int i = 1; i <= testCount; i++)
			solver.solve(i, in, out);
		Exit.exit(in, out);
	}
}


class MainChecker {
	public static String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {
		return new BickeringCooksChecker().check(input, expectedOutput, actualOutput);
	}

	public static Collection<Test> generateTests() {
		return new BickeringCooksChecker().generateTests();
	}
}

