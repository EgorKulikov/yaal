package on2012_12.on2012_12_20_Volume_3._1211___Collective_Guarantee;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Task1211 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] who = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(who);
		int[] processed = new int[count];
		Arrays.fill(processed, count);
		int self = 0;
		for (int i = 0; i < count; i++) {
			if (processed[i] != count)
				continue;
			int j = i;
			while (j != -1 && processed[j] == count) {
				processed[j] = who[j];
				j = processed[j];
			}
			if (j != -1 && processed[j] != -1) {
				out.printLine("NO");
				return;
			}
			if (j == -1)
				self++;
			j = i;
			while (j != -1 && processed[j] != -1) {
				processed[j] = -1;
				j = who[j];
			}
		}
		if (self == 1)
			out.printLine("YES");
		else
			out.printLine("NO");
	}
}
