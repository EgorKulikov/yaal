package on2012_07.on2012_6_23.taskb;



import net.egork.collections.map.EHashMap;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class TaskB {
	private String[] cards;
	Map<State, Boolean> map = new EHashMap<State, Boolean>();

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 1) {
			out.printLine("YES");
			return;
		}
		cards = IOUtils.readStringArray(in, count);
		if (count == 2) {
			if (compatible(cards[0], cards[1]))
				out.printLine("YES");
			else
				out.printLine("NO");
			return;
		}
		if (go(cards[count - 1], cards[count - 2], cards[count - 3], cards.length - 3))
			out.printLine("YES");
		else
			out.printLine("NO");
	}

	private boolean go(String last, String preLast, String prePreLast, int remaining) {
		State key = new State(last, preLast, prePreLast, remaining);
		if (map.containsKey(key))
			return map.get(key);
		if (remaining == 0) {
			if (compatible(last, preLast) && compatible(last, prePreLast)) {
				map.put(key, true);
				return true;
			} else {
				map.put(key, false);
				return false;
			}
		}
		if (compatible(last, preLast) && go(last, prePreLast, cards[remaining - 1], remaining - 1)) {
			map.put(key, true);
			return true;
		}
		if (compatible(last, cards[remaining - 1]) && go(preLast, prePreLast, last, remaining - 1)) {
			map.put(key, true);
			return true;
		}
		map.put(key, false);
		return false;
	}

	private boolean compatible(String last, String preLast) {
		return last.charAt(0) == preLast.charAt(0) || last.charAt(1) == preLast.charAt(1);
	}

	static class State {
		String last;
		String preLast;
		String prePreLast;
		int remaining;

		State(String last, String preLast, String prePreLast, int remaining) {
			this.last = last;
			this.preLast = preLast;
			this.prePreLast = prePreLast;
			this.remaining = remaining;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			State state = (State) o;

			if (remaining != state.remaining) return false;
			if (!last.equals(state.last)) return false;
			if (!preLast.equals(state.preLast)) return false;
			if (!prePreLast.equals(state.prePreLast)) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = last.hashCode();
			result = 31 * result + preLast.hashCode();
			result = 31 * result + prePreLast.hashCode();
			result = 31 * result + remaining;
			return result;
		}
	}
}
