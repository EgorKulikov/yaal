package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class DivisibleNumbersSpanClassacmchallengeballoonlargeStylebackgroundcolorNullspan {
//	static int maxAnswer;
	int number;
//	static OutputWriter writer;
	Map<State, Boolean> set;
	boolean special;
	static int[] answer = new int[30001];

	static {
		answer[2275] = 90;
		answer[2405] = 56;
		answer[2525] = 92;
		answer[2775] = 189;
		answer[3125] = 139;
		answer[3885] = 109;
		answer[5555] = 129;
		answer[6825] = 109;
		answer[7215] = 64;
		answer[7575] = 92;
		answer[7585] = 53;
		answer[8325] = 195;
		answer[9298] = 81;
		answer[9375] = 139;
		answer[9435] = 61;
		answer[9485] = 52;
		answer[9555] = 50;
		answer[10175] = 56;
		answer[10545] = 61;
		answer[10915] = 38;
		answer[11375] = 90;
		answer[11396] = 60;
		answer[11655] = 114;
		answer[12025] = 56;
		answer[12285] = 72;
		answer[12395] = 38;
		answer[12625] = 92;
		answer[12765] = 61;
		answer[13475] = 40;
		answer[13875] = 189;
		answer[14245] = 131;
		answer[14905] = 52;
		answer[14985] = 78;
		answer[15925] = 90;
		answer[17094] = 60;
		answer[18596] = 81;
		answer[19425] = 189;
		answer[21875] = 144;
		answer[22528] = 56;
		answer[22792] = 60;
		answer[24975] = 204;
		answer[27195] = 109;
		answer[15625] = 465;
		answer[27894] = 95;
		answer[28125] = 142;
		answer[29575] = 90;
		answer[27775] = 705;
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		number = in.readInt();
		if (answer[number] != 0) {
			out.printLine(answer[number]);
			return;
		}
		special = number % 5 == 0;
		int left = 1;
		int right = 1;
		set = new HashMap<State, Boolean>();
		while (!can(right)) {
			left = right + 1;
			right *= 2;
		}
		while (left < right) {
			int middle = (left + right) >> 1;
			if (can(middle))
				right = middle;
			else
				left = middle + 1;
		}
		out.printLine(left);
	}

	static class State {
		final int steps;
		final int remainder;
		final int product;
		final int sum;

		State(int steps, int remainder, int product, int sum) {
			this.steps = steps;
			this.remainder = remainder;
			this.product = product;
			this.sum = sum;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			State state = (State) o;

			if (product != state.product) return false;
			if (remainder != state.remainder) return false;
			if (steps != state.steps) return false;
			if (sum != state.sum) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = steps;
			result = 31 * result + remainder;
			result = 31 * result + product;
			result = 31 * result + sum;
			return result;
		}
	}

	private boolean can(int steps) {
//		System.err.println(steps);
		return can(steps, 0, 1, 0);
	}

	private boolean can(int steps, int remainder, int product, int sum) {
		if (sum != 0 && remainder == 0 && sum >= product)
			return true;
		if (steps == 0)
			return false;
		if (special && product * 5 > sum + steps + 4)
			return false;
		if (steps > 15 && product * 2 <= sum + steps + 1) {
			State state = new State(steps, remainder, product, sum);
			if (set.containsKey(state))
				return set.get(state);
		}
		boolean result = false;
		for (int i = 1; i <= 9; i++) {
			if (product * i > sum + steps - 1 + i)
				break;
			if (can(steps - 1, (remainder * 10 + i) % number, product * i, sum + i)) {
				result = true;
				break;
			}
		}
		if (steps > 15 && product * 2 <= sum + steps + 1) {
			State state = new State(steps, remainder, product, sum);
			set.put(state, result);
		}
		return result;
	}
}
