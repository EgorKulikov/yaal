package on2013_09.on2013_09_28_COCI_Round__1.TaskB;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int sausageCount = in.readInt();
		int tasterCount = in.readInt();
		int gcd = IntegerUtils.gcd(sausageCount, tasterCount);
		int answer = tasterCount - gcd;
		out.printLine(answer);
    }
}
