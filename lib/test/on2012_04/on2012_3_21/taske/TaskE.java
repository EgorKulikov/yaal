package on2012_04.on2012_3_21.taske;



import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int days = in.readInt() - count;
		if (days < 0) {
			out.printLine(0);
			return;
		}
		int[] allowed = new int[count];
		int[] population = new int[count];
		IOUtils.readIntArrays(in, allowed, population);
		if (CollectionUtils.count(Array.wrap(allowed), 0) == count) {
			if (days == 0)
				out.printLine(-1);
			else
				out.printLine(0);
			return;
		}
		out.printLine(go(count, days, allowed, population) - go(count, days - 1, allowed, population));
	}

	private long go(int count, int days, int[] allowed, int[] population) {
		long left = 0;
		long right = Long.MAX_VALUE / 2;
		while (right > left) {
			long middle = (left + right + 1) / 2;
			long remainingDays = days;
			for (int i = 0; i < count; i++) {
				if (allowed[i] != 0 && Long.MAX_VALUE / allowed[i] < middle) {
					remainingDays = -1;
					break;
				}
				remainingDays -= allowed[i] * middle / population[i];
				if (remainingDays < 0)
					break;
			}
			if (remainingDays < 0)
				right = middle - 1;
			else
				left = middle;
		}
		return left;
	}
}
