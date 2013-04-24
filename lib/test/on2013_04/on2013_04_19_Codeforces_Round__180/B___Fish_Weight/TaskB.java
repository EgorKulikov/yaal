package on2013_04.on2013_04_19_Codeforces_Round__180.B___Fish_Weight;



import net.egork.collections.map.Counter;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int countAlice = in.readInt();
		int countRobert = in.readInt();
		int count = in.readInt();
		Counter<Integer> delta = new Counter<Integer>();
		for (int i = 0; i < countAlice; i++)
			delta.add(in.readInt() - 1);
		for (int i = 0; i < countRobert; i++)
			delta.add(in.readInt() - 1, -1);
		Integer[] keys = delta.keySet().toArray(new Integer[delta.size()]);
		Arrays.sort(keys);
		int sum = 0;
		for (int i = keys.length - 1; i >= 0; i--) {
			sum += delta.get(keys[i]);
			if (sum > 0) {
				out.printLine("YES");
				return;
			}
		}
		out.printLine("NO");
    }
}
