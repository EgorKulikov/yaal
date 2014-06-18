package on2014_06.on2014_06_13_Yandex_Algorithm_2014_Round_3_Test_Run.C____________;



import net.egork.collections.intcollection.IntHashMap;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int delta = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		IntHashMap map = new IntHashMap();
		long answer = (long)count * (count + 1) / 2;
		int from = 0;
		for (int i = 0; i < count; i++) {
			if (map.contains(array[i] - delta))
				from = Math.max(from, map.get(array[i] - delta) + 1);
			if (map.contains(array[i] + delta))
				from = Math.max(from, map.get(array[i] + delta) + 1);
			answer -= i - from + 1;
			map.put(array[i], i);
		}
		out.printLine(answer);
    }
}
