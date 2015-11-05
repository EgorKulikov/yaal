package on2015_10.on2015_10_31_Yekaterinbirg_GP.TaskG;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = ArrayUtils.count(in.readLine().toCharArray(), '!');
		if (count == 0) {
			out.printLine("Pfff");
		} else {
			out.print("W");
			for (int i = 0; i < count; i++) {
				out.print('o');
			}
			out.printLine("w");
		}
	}
}
