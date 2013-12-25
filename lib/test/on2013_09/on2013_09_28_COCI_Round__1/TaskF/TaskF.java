package on2013_09.on2013_09_28_COCI_Round__1.TaskF;



import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readInt();
		String ingredients = in.readString();
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			String sample = in.readString();
			String all = sample + ingredients;
			int[] z = StringUtils.zAlgorithm(all);
			int result = 0;
			for (int j = sample.length(); j < z.length; j++) {
				if (z[j] >= sample.length()) {
					result += sample.length();
					break;
				}
				result += z[j] + 1;
			}
			out.printLine(result);
		}
    }
}
