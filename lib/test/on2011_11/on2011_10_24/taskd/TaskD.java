package on2011_11.on2011_10_24.taskd;



import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String sample = in.readString();
		String target = in.readString();
		String combined = sample + "$" + target;
		int length = sample.length();
		int[] z = StringUtils.zAlgorithm(combined);
		int answer = 0;
		for (int i = length + 1; i < z.length; i++) {
			if (z[i] == length)
				answer++;
		}
		out.printLine(answer);
	}
}
