package on2012_12.on2012_12_26_Volume_4._1354___Palindrome__Again_Palindrome;



import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1354 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.readString();
        String sample = StringUtils.reverse(s) + '#' + s;
        int[] z = StringUtils.zAlgorithm(sample);
        for (int i = s.length() + 2; i < z.length; i++) {
            if (i + z[i] == z.length) {
                out.printLine(s + StringUtils.reverse(s.substring(0, s.length() - z.length + i)));
                return;
            }
        }
        out.printLine(s + s);
    }
}
