package on2016_01.on2016_01_11_CodeBar.Semusa_Cipher;



import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class SemusaCipher {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = in.readIntArray(n);
		Arrays.sort(a);
		IntList answer = new IntArrayList(n);
		for (int i = n - 1, j = 0; i >= j; i--, j++) {
			answer.add(a[i]);
			if (i != j) {
				answer.add(a[j]);
			}
		}
		out.printLine(answer);
	}
}
