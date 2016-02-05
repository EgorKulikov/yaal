package on2015_12.on2015_12_30_Good_Bye_2015.B___New_Year_and_Old_Property;



import net.egork.numbers.NumberIterator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	long answer;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long a = in.readLong();
		long b = in.readLong();
		new NumberIterator(2) {
			@Override
			protected void process(long prefix, int remainingDigits) {
				int bitCount = Long.bitCount(prefix);
				int total = Long.bitCount(Long.highestOneBit(prefix) - 1) + 1;
				if (bitCount == total) {
					answer += remainingDigits;
				} else if (bitCount == total - 1) {
					answer++;
				}
			}
		}.run(a, b);
		out.printLine(answer);
	}
}
