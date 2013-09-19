package on2013_09.on2013_09_September_Challenge_2013.Cool_Guys;



import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CoolGuys {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int end = 0;
		long answer = 0;
		for (int i = 1; i * i <= count; i++) {
			answer += count / i;
			end = i;
		}
		for (int i = count / (end + 1); i > 0; i--) {
			int until = count / i;
			answer += (long) (until - end) * i;
			end = until;
		}
		out.printLine(new Rational(answer, (long)count * count));
    }
}
