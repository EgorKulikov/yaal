package on2016_01.on2016_01_11_CodeBar.Count_The_Prime;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class CountThePrime {
	int[] p = IntegerUtils.generatePrimes(1000000);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x1 = in.readInt();
		int x2 = in.readInt();
		int f1 = Arrays.binarySearch(p, x1);
		if (f1 < 0) {
			f1 = -f1 - 1;
		}
		int f2 = Arrays.binarySearch(p, x2);
		if (f2 < 0) {
			f2 = -f2 - 2;
		}
		out.printLine(f2 - f1 + 1);
	}
}
