package net.egork;

import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class PermuteDigits {
	private Map<State,Long> map;
	private char[] c;
	private int base;

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		base = in.readInt();
		char[] a = in.readString().toCharArray();
		char[] b = in.readString().toCharArray();
		c = in.readString().toCharArray();
		long first = 0;
		for (char digit : a)
			first += 1L << (6 * (digit - '0'));
		long second = 0;
		for (char digit : b)
			second += 1L << (6 * (digit - '0'));
		map = new HashMap<State, Long>();
		out.println(go(new State(c.length - 1, false, first, second)));
	}

	private long go(State state) {
		if (map.containsKey(state))
			return map.get(state);
		if (state.step == -1) {
			if (state.shift || state.first >= 64 || state.second >= 64) {
				map.put(state, 0L);
				return 0;
			}
			map.put(state, 1L);
			return 1;
		}
		long result = 0;
		int sum = c[state.step] - '0';
		if (state.shift)
			sum--;
		for (int i = 0; i < base; i++) {
			int j = sum - i;
			if (j >= 0 && j < base && (state.first >> (6 * i) & 63) != 0 && (state.second >> (6 * j) & 63) != 0)
				result += go(state.next(i, j, false));
		}
		sum += base;
		for (int i = 0; i < base; i++) {
			int j = sum - i;
			if (j >= 0 && j < base && (state.first >> (6 * i) & 63) != 0 && (state.second >> (6 * j) & 63) != 0)
				result += go(state.next(i, j, true));
		}
		map.put(state, result);
		return result;
	}
}

class State {
	final int step;
	final boolean shift;
	final long first;
	final long second;

	State(int step, boolean shift, long first, long second) {
		this.step = step;
		this.shift = shift;
		if (first != 0)
			this.first = first;
		else
			this.first = 1;
		if (second != 0)
			this.second = second;
		else
			this.second = 1;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		State state = (State) o;

		if (first != state.first) return false;
		if (second != state.second) return false;
		if (shift != state.shift) return false;
		if (step != state.step) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = step;
		result = 31 * result + (shift ? 1 : 0);
		result = 31 * result + (int) (first ^ (first >>> 32));
		result = 31 * result + (int) (second ^ (second >>> 32));
		return result;
	}

	public State next(int i, int j, boolean shift) {
		return new State(step - 1, shift, first - (1L << (6 * i)), second - (1L << 6 * j));
	}
}