package on2014_05.on2014_05_23_Yandex_Algorithm_2014__Qualification__Test_run.F___________;



import net.egork.collections.map.Counter;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Counter<String> counter = new Counter<>();
		for (int i = 0; i < count - 1; i++) {
			counter.add(in.readString());
			in.readString();
		}
		String answer = null;
		long max = 0;
		for (Map.Entry<String, Long> entry : counter.entrySet()) {
			if (entry.getValue() > max) {
				max = entry.getValue();
				answer = entry.getKey();
			}
		}
		out.printLine(answer);
	}
}
