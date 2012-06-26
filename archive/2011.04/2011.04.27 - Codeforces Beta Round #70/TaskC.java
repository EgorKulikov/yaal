import net.egork.collections.CollectionUtils;
import net.egork.collections.filter.Filter;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int length = in.readInt();
		final int minLength = in.readInt();
		if (count % 2 == 0) {
			out.println("Marsel");
			return;
		}
		WritableSequence<Long> divisors = ListWrapper.wrap(CollectionUtils.filter(IntegerUtils.getDivisors(length), new Filter<Long>() {
			public boolean accept(Long value) {
				return value >= minLength;
			}
		}));
		SequenceUtils.sort(divisors);
		Map<Long, Integer> nimber = new HashMap<Long, Integer>();
		nimber.put((long)length, 0);
		for (long divisor : divisors) {
			Set<Integer> nimbers = new HashSet<Integer>();
			for (long otherDivisor : divisors) {
				if (divisor == otherDivisor)
					break;
				if (divisor % otherDivisor == 0)
					nimbers.add(divisor / otherDivisor % 2 == 0 ? 0 : nimber.get(otherDivisor));
			}
			for (int i = 0; ; i++) {
				if (!nimbers.contains(i)) {
					nimber.put(divisor, i);
					break;
				}
			}
		}
		if (nimber.get((long)length) == 0)
			out.println("Marsel");
		else
			out.println("Timur");
	}
}

