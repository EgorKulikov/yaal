package on2014_03.on2014_03_30_Codeforces_Round__239__Div__1_.B___Long_Path;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	private static final int MOD = (int) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] back = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(back);
		int[] answer = new int[count];
		for (int i = 0; i < count; i++) {
			answer[i] = 2;
			for (int j = back[i]; j < i; j++) {
				answer[i] += answer[j];
				if (answer[i] >= MOD)
					answer[i] -= MOD;
			}
		}
		long result = ArrayUtils.sumArray(answer);
		out.printLine(result % MOD);
    }
}
