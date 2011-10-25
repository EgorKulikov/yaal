import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class TaskA implements Solver {
	private int pageCount;

	private static class Result {
		private final long state;
		private final long value;

		private Result(long state, long value) {
			this.state = state;
			this.value = value;
		}
	}

	private static class Argument {
		private final long state;
		private final int index;

		private Argument(long state, int index) {
			this.state = state;
			this.index = index;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;

			Argument argument = (Argument) o;

			if (index != argument.index)
				return false;
			if (state != argument.state)
				return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = (int) (state ^ (state >>> 32));
			result = 31 * result + index;
			return result;
		}
	}

	private Map<Argument, Result> result = new HashMap<Argument, Result>();

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		pageCount = in.readInt();
		AtomicLong state = new AtomicLong(Long.parseLong(in.readString(), 2));
		long finalState = Long.parseLong(in.readString(), 2);
		long result = 0;
		for (int i = 0; i < pageCount; i++) {
			if ((state.get() >> i & 1) != (finalState >> i & 1))
				result += adjust(i, state);
		}
		out.println(result);
	}

	private long adjust(int index, AtomicLong state) {
		Argument argument = new Argument(state.get() & (~((1L << (index + 1)) - 1)), index);
		if (result.containsKey(argument)) {
			state.set(state.get() ^ result.get(argument).state);
			return result.get(argument).value;
		}
		if (index == pageCount - 1) {
			state.set(state.get() ^ (1L << (pageCount - 1)));
			result.put(argument, new Result(1L << (pageCount - 1), 1));
			return 1;
		}
		long result = 0;
		long startState = state.get();
		if ((state.get() >> (index + 1) & 1) == 0)
			result = adjust(index + 1, state);
		for (int i = index + 2; i < pageCount; i++) {
			if ((state.get() >> i & 1) == 1)
				result += adjust(i, state);
		}
		state.set(state.get() ^ (1L << index));
		this.result.put(argument, new Result(state.get() ^ startState, result + 1));
		return result + 1;
	}
}

