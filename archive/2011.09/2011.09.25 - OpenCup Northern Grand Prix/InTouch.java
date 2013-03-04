import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class InTouch implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		int eventCount = in.readInt();
		int[] eventType = new int[eventCount];
		int[] firstArgument = new int[eventCount];
		int[] secondArgument = new int[eventCount];
		for (int i = 0; i < eventCount; i++) {
			char event = in.readCharacter();
			firstArgument[i] = in.readInt() - 1;
			if (event == '!') {
				eventType[i] = 0;
			} else {
				secondArgument[i] = in.readInt() - 1;
				if (event == '+')
					eventType[i] = 1;
				else
					eventType[i] = 2;
			}
		}
		int[] numEvents = new int[count];
		for (int i = 0; i < eventCount; i++) {
			numEvents[firstArgument[i]]++;
			if (eventType[i] != 0)
				numEvents[secondArgument[i]]++;
		}
		int[][] events = new int[count][];
		for (int i = 0; i < count; i++)
			events[i] = new int[numEvents[i]];
		for (int i = 0; i < eventCount; i++) {
			events[firstArgument[i]][--numEvents[firstArgument[i]]] = i;
			if (eventType[i] != 0)
				events[secondArgument[i]][--numEvents[secondArgument[i]]] = i;
		}
		int[] answer = new int[count];
		for (int i = 0; i < count; i++) {
			Map<Integer, Integer> delta = new HashMap<Integer, Integer>();
			int broadcasts = 0;
			for (int j : events[i]) {
				int other = firstArgument[j] + secondArgument[j] - i;
				if (eventType[j] >= 1) {
					if (delta.containsKey(other))
						delta.remove(other);
					else
						delta.put(other, 3 - 2 * eventType[j]);
				} else {
					for (Map.Entry<Integer, Integer> entry : delta.entrySet())
						answer[entry.getKey()] += broadcasts * entry.getValue();
					delta.clear();
					broadcasts++;
				}
			}
			for (Map.Entry<Integer, Integer> entry : delta.entrySet())
				answer[entry.getKey()] += broadcasts * entry.getValue();
		}
		out.print(answer[0]);
		for (int i = 1; i < count; i++)
			out.print(" " + answer[i]);
		out.println();
	}
}

