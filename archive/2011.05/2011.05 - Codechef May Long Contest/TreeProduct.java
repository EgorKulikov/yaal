import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.Exit;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.math.BigInteger;

public class TreeProduct implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int height = in.readInt();
		if (height == 0) {
			Exit.exit(in, out);
			return;
		}
		int[] numbers = IOUtils.readIntArray(in, (1 << height) - 1);
		BigInteger[] result = new BigInteger[(1 << height) - 1];
		result[0] = BigInteger.valueOf(numbers[0]);
		for (int i = 1; i < (1 << height) - 1; i++)
			result[i] = result[(i - 1) / 2].multiply(BigInteger.valueOf(numbers[i]));
		out.println(CollectionUtils.maxElement(Array.wrap(result)).mod(BigInteger.valueOf(1000000007)));
	}
}

