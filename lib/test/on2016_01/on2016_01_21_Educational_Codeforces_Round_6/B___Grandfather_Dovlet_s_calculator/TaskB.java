package on2016_01.on2016_01_21_Educational_Codeforces_Round_6.B___Grandfather_Dovlet_s_calculator;



import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.numbers.NumberIterator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	int[] cost = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
	long totalCost = ArrayUtils.sumArray(cost);
	int answer = 0;
	long[] ten = IntegerUtils.generatePowers(10, (long) 1e9);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		new NumberIterator() {
			@Override
			protected void process(long prefix, int remainingDigits) {
				int prCost = 0;
				while (prefix != 0) {
					prCost += cost[(int) (prefix % 10)];
					prefix /= 10;
				}
				answer += prCost * ten[remainingDigits];
				if (remainingDigits == 0) {
					return;
				}
				answer += totalCost * ten[remainingDigits - 1] * remainingDigits;
			}
		}.run(a, b);
		out.printLine(answer);
	}
}
