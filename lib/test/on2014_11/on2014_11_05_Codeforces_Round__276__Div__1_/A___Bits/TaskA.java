package on2014_11.on2014_11_05_Codeforces_Round__276__Div__1_.A___Bits;



import net.egork.numbers.NumberIterator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	long answer;
	NumberIterator iterator = new NumberIterator(2) {
		@Override
		protected void process(long prefix, int remainingDigits) {
			long candidate = ((prefix + 1) << remainingDigits) - 1;
			if (Long.bitCount(candidate) > Long.bitCount(answer) || Long.bitCount(candidate) == Long.bitCount(answer) && candidate < answer) {
				answer = candidate;
			}
		}
	};

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long from = in.readLong();
		long to = in.readLong();
		answer = 0;
		iterator.run(from, to);
		out.printLine(answer);
    }
}
