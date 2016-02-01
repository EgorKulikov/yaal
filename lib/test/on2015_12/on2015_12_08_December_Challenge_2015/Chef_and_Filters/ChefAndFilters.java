package on2015_12.on2015_12_08_December_Challenge_2015.Chef_and_Filters;



import net.egork.generated.collections.comparator.IntComparator;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndFilters {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int start = Integer.parseInt(in.readString().replace('w', '1').replace('b', '0'), 2);
		int n = in.readInt();
		int[] filters = new int[n];
		for (int i = 0; i < n; i++) {
			filters[i] = Integer.parseInt(in.readString().replace('+', '1').replace('-', '0'), 2);
		}
		for (int i = 0; i < 10 && i < n; i++) {
			ArrayUtils.sort(filters, i, n, IntComparator.REVERSE);
			int highest = Integer.highestOneBit(filters[i]);
			for (int j = i + 1; j < n; j++) {
				if ((filters[j] & highest) != 0) {
					filters[j] ^= filters[i];
				}
			}
			if ((start & highest) != 0) {
				start ^= filters[i];
			}
		}
		if (start == 0) {
			out.printLine(IntegerUtils.power(2, ArrayUtils.count(filters, 0), MOD));
		} else {
			out.printLine(0);
		}
	}
}
