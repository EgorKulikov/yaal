import net.egork.collections.Pair;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class TaskD implements Solver {
	private Map<Pair<Integer, Integer>, Integer> map = new HashMap<Pair<Integer, Integer>, Integer>();
	private int[] primes;

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int from = in.readInt();
		int to = in.readInt();
		int divisor = in.readInt();
		if (!BigInteger.valueOf(divisor).isProbablePrime(50))
			out.println(0);
		else if (to / divisor < divisor) {
			if (from <= divisor && to >= divisor)
				out.println(1);
			else
				out.println(0);
		} else {
			primes = IntegerUtils.generatePrimes(divisor + 1);
			out.println(go(primes.length - 1, to) - go(primes.length - 1, from - 1));
		}
	}

	private int go(int index, int to) {
		to /= primes[index];
		Pair<Integer, Integer> key =Pair.makePair(index, to);
		if (map.containsKey(key))
			return map.get(key);
		int result = to;
		for (int i = 0; i < index; i++)
			result -= go(i, to);
		map.put(key, result);
		return result;
	}
}

