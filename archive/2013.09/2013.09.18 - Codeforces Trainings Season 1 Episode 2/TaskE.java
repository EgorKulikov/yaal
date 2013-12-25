package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int degree = in.readInt();
		if (degree == 0)
			throw new UnknownError();
		int[] coefficients = IOUtils.readIntArray(in, degree + 1);
		int x = in.readInt();
		int value = 0;
		for (int i : coefficients) {
			value *= x;
			value += i;
		}
		int operations = -1;
		for (int i = 1; i <= degree; i++) {
			operations += 2;
			if (coefficients[i] != 0) {
				operations++;
				operations += Integer.toString(coefficients[i]).length();
			}
		}
		operations++;
		out.printLine("Polynomial", testNumber + ":", value, operations);
    }
}
