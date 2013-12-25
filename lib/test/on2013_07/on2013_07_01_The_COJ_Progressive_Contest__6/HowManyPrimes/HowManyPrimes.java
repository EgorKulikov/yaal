package on2013_07.on2013_07_01_The_COJ_Progressive_Contest__6.HowManyPrimes;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class HowManyPrimes {
	int[] primes = IntegerUtils.generatePrimes(1000000);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int from = in.readInt();
		int to = in.readInt();
		if (from == 0 && to == 0)
			throw new UnknownError();
		from = Arrays.binarySearch(primes, from);
		to = Arrays.binarySearch(primes, to);
		if (from < 0)
			from = -from - 1;
		if (to < 0)
			to = -to - 2;
		out.printLine(to - from + 1);
    }
}
