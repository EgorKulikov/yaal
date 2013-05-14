package on2013_04.on2013_04_13_Russian_CodeCup_Qualification_Round__1.TaskC;



import net.egork.collections.sequence.Array;
import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Set;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long count = in.readLong();
		int restrictionCount = in.readInt();
		if (count == 0 && restrictionCount == 0)
			throw new UnknownError();
		long[] restrictions = IOUtils.readLongArray(in, restrictionCount);
		long wildcards = 1;
		long current = 1;
		Set<Long> set = new EHashSet<Long>(Array.wrap(restrictions));
		while (current <= count) {
			if (!set.contains(2 * current))
				wildcards += Math.min(2 * current, count) - current;
			else if (2 * current <= count)
				wildcards++;
			current *= 2;
		}
		out.printLine(IntegerUtils.power(2, wildcards, (long) (1e9 + 7)));
	}
}
