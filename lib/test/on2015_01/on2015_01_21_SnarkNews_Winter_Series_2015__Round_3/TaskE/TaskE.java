package on2015_01.on2015_01_21_SnarkNews_Winter_Series_2015__Round_3.TaskE;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int size = in.readInt();
		int answer = 0;
		while (count > 1) {
			int gcd = IntegerUtils.gcd(count, size);
			answer++;
			if (gcd == 1) {
				out.printLine(-1);
				return;
			}
			count /= gcd;
		}
		out.printLine(answer);
    }
}
