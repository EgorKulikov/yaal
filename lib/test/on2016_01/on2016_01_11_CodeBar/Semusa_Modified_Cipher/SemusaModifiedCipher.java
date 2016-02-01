package on2016_01.on2016_01_11_CodeBar.Semusa_Modified_Cipher;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SemusaModifiedCipher {
	long[] f = IntegerUtils.generateFibonacci(88, 26);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		for (int i = 0; i < n; i++) {
			if (f[i] == 0) {
				f[i] = 26;
			}
			char c = (char) (f[i] - 1 + 'A');
			out.print(c);
		}
		out.printLine();
	}
}
