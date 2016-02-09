package on2016_01.on2016_01_14_Codeforces_Round__339__Div__1_.C___Necklace;



import net.egork.generated.collections.function.IntIntToIntFunction;
import net.egork.generated.collections.list.IntArray;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = IOUtils.readIntArray(in, n);
		int odd = new IntArray(a).count(x -> x % 2 == 1);
		if (odd >= 2) {
			out.printLine(0);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < a[i]; j++) {
					out.print((char)('a' + i));
				}
			}
			out.printLine();
			return;
		}
		int gcd = new IntArray(a).reduce(0, (IntIntToIntFunction) IntegerUtils::gcd);
		out.printLine(gcd);
		if (gcd % 2 == 0) {
			gcd /= 2;
		}
		for (int i = 0; i < n; i++) {
			a[i] /= gcd;
		}
		for (int i = 0; i < gcd; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < (a[j] >> 1); k++) {
					out.print((char)('a' + j));
				}
			}
			for (int j = 0; j < n; j++) {
				if ((a[j] & 1) == 1) {
					out.print((char)('a' + j));
				}
			}
			for (int j = n - 1; j >= 0; j--) {
				for (int k = 0; k < (a[j] >> 1); k++) {
					out.print((char)('a' + j));
				}
			}
		}
		out.printLine();
	}
}
