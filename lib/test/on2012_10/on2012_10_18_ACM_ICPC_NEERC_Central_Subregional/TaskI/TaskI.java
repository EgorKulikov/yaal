package on2012_10.on2012_10_18_ACM_ICPC_NEERC_Central_Subregional.TaskI;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 1) {
			out.printLine(1);
			out.printLine(1);
			return;
		}
		int enough;
		for (int i = 1; ; i++) {
			if (IntegerUtils.generateBinomialCoefficients(i + 1)[i][i >> 1] >= count) {
				enough = i;
				break;
			}
		}
		List<Integer>[] answer = new List[enough];
		for (int i = 0; i < enough; i++)
			answer[i] = new ArrayList<Integer>();
		int j = 0;
		for (int i = 0; j < count; i++) {
			if (Integer.bitCount(i) != (enough >> 1))
				continue;
			for (int k = 0; k < enough; k++) {
				if ((i >> k & 1) == 1)
					answer[k].add(j + 1);
			}
			j++;
		}
		out.printLine(enough);
		for (int i = 0; i < enough; i++)
			out.printLine(answer[i].toArray());
	}
}
