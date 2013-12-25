package on2012_09.on2012_09_19_Codeforces_Round__139__Div__2_.E___Unsolvable;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	String numbers = "2,3,5,7,13,17,19,31,61,89,107,127,521,607,1279,2203,2281,3217,4253,4423,9689,9941,11213,19937,21701,23209,44497,86243,110503,132049,216091,756839,859433,1257787,1398269,2976221,3021377,6972593,13466917,20996011,24036583";

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int index = in.readInt();
		int exponent = Integer.parseInt(numbers.split(",")[index - 1]) - 1;
		out.printLine(IntegerUtils.power(2, exponent, 1000000007) - 1);
	}
}
