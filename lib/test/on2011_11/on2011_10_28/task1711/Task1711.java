package on2011_11.on2011_10_28.task1711;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class Task1711 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[][] names = IOUtils.readStringTable(in, count, 3);
		int[] order = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(order);
		List<String> answer = new ArrayList<String>();
		for (int i : order) {
			String current = null;
			for (String variant : names[i]) {
				if ((answer.isEmpty() || variant.compareTo(answer.get(answer.size() - 1)) > 0) &&
					(current == null || variant.compareTo(current) < 0))
				{
					current = variant;
				}
			}
			if (current == null) {
				out.printLine("IMPOSSIBLE");
				return;
			}
			answer.add(current);
		}
		for (String keyword : answer)
			out.printLine(keyword);
	}
}
