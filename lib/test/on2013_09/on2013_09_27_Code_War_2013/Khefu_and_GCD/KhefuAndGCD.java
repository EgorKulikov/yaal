package on2013_09.on2013_09_27_Code_War_2013.Khefu_and_GCD;



import net.egork.numbers.MultiplicativeFunction;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class KhefuAndGCD {
	long[] answer;

	{
		answer = MultiplicativeFunction.PHI.calculateUpTo(10000000);
		answer[0] = 0;
		answer[1] = 3;
		for (int i = 2; i < 10000000; i++)
			answer[i] = answer[i - 1] + 2 * answer[i];
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(answer[in.readInt() - 1]);
    }
}
