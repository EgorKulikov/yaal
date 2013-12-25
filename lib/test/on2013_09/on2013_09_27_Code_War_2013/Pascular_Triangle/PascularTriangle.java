package on2013_09.on2013_09_27_Code_War_2013.Pascular_Triangle;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PascularTriangle {
	long[] fac = IntegerUtils.generateReverseFactorials(17, 1000000007);

	final long c(int n, int m) {
		long result = 1;
		for (int i = 0; i < m; i++)
			result = result * (n - i) % 1000000007;
		result = result * fac[m] % 1000000007;
		return result;
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int row = in.readInt();
		int column = in.readInt();
		long answer = c(row + 1, column) - c(row - 1, column - 1);
		if (answer < 0)
			answer += 1000000007;
		out.printLine(answer);
    }
}
