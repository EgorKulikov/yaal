package on2015_12.on2015_12_08_December_Challenge_2015.Oracle_Devu_and_Longest_Common_Subsequence;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class OracleDevuAndLongestCommonSubsequence {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] as = new int[n];
		int[] bs = new int[n];
		for (int i = 0; i < n; i++) {
			for (char c : in.readString().toCharArray()) {
				if (c == 'a') {
					as[i]++;
				} else {
					bs[i]++;
				}
			}
		}
		out.printLine(Math.min(ArrayUtils.minElement(as), ArrayUtils.minElement(bs)));
	}
}
