package on2013_09.on2013_09_27_Code_War_2013.P_A_N_C_A_K_E_Sorting;



import net.egork.collections.map.EHashMap;
import net.egork.io.IOUtils;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Map;
import java.util.Queue;

public class PANCAKESorting {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int revertLength = in.readInt();
		char[] start = IOUtils.readCharArray(in, length);
		String initial = new String(start);
		Arrays.sort(start);
		String target = StringUtils.reverse(new String(start));
		Queue<String> queue = new ArrayDeque<String>();
		queue.add(initial);
		Map<String, Integer> answer = new EHashMap<String, Integer>();
		answer.put(initial, 0);
		while (!queue.isEmpty()) {
			String current = queue.poll();
			int distance = answer.get(current);
			if (target.equals(current)) {
				out.printLine(distance);
				return;
			}
			for (int i = 0; i <= length - revertLength; i++) {
				String candidate = current.substring(0, i) + StringUtils.reverse(current.substring(i, i + revertLength))
					+ current.substring(i + revertLength);
				if (!answer.containsKey(candidate)) {
					answer.put(candidate, distance + 1);
					queue.add(candidate);
				}
			}
		}
		out.printLine("NP");
    }
}
