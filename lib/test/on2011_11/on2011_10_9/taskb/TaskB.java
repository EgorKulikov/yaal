package on2011_11.on2011_10_9.taskb;



import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		int[] z = StringUtils.zAlgorithm(s);
		int maxInside = 0;
		for (int i = 1; i < z.length; i++)
			maxInside = Math.max(maxInside, Math.min(z[i], z.length - i - 1));
		int answer = -1;
		for (int i = z.length - maxInside; i < z.length; i++) {
			if (i + z[i] == z.length) {
				answer = z.length - i;
				break;
			}
		}
		if (answer == -1)
			out.printLine("Just a legend");
		else
			out.printLine(s.substring(0, answer));
	}
}
