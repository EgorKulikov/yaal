package on2013_07.on2013_07_11_Yandex_Algorithm_Online_Round__1.TaskC;



import net.egork.collections.Pair;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.List;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		int count = in.readInt();
		List<Pair<Long,Integer>> list = IntegerUtils.factorize(number);
		int total = 0;
		for (Pair<Long, Integer> pair : list) {
			total += pair.second;
		}
		if (count > total || list.size() == 1 && count % 2 != total % 2)
			out.printLine("NO");
		else
			out.printLine("YES");
	}
}
