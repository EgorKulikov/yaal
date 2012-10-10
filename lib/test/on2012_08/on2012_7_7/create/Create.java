package on2012_08.on2012_7_7.create;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Create {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        if (count == 1) {
            out.printLine(0);
            return;
        }
        int[] money = IOUtils.readIntArray(in, count);
        long gcd = 0;
        for (int i = 1; i < count; i++)
            gcd = IntegerUtils.gcd(gcd, money[i] - money[0]);
        long answer = (money[count - 1] - money[0]) / gcd + 1 - count;
        out.printLine(answer);
	}
}
