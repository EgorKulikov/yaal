import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class Misinterpretation implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int length = in.readInt();
		int[] permutation = new int[length];
		int index = 0;
		for (int i = 1; i < length; i += 2)
			permutation[index++] = i;
		for (int i = 0; i < length; i += 2)
			permutation[index++] = i;
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(length);
		for (int i = 0; i < length; i++)
			setSystem.join(i, permutation[i]);
		out.println(IntegerUtils.power(26, setSystem.getSetCount(), 1000000007));
	}
}

