import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

public class TaskJ implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		int length = in.readInt();
		int transitionCount = in.readInt();
		int[][] transition = new int[count][count];
		for (int i = 0; i < transitionCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			in.readCharacter();
			transition[from][to]++;
		}
		BigInteger[] result = new BigInteger[count];
		Arrays.fill(result, BigInteger.ZERO);
		result[0] = BigInteger.ONE;
		BigInteger[] next = new BigInteger[count];
		for (int i = 0; i < length; i++) {
			Arrays.fill(next, BigInteger.ZERO);
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++) {
					if (transition[j][k] != 0)
						next[k] = next[k].add(result[j].multiply(BigInteger.valueOf(transition[j][k])));
				}
			}
			BigInteger[] temp = result;
			result = next;
			next = temp;
		}
		out.println(result[0]);
	}
}

