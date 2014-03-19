package on2014_02.on2014_02_26_Codeforces_Round__232__Div__1_.E___On_Iteration_of_One_Well_Known_Function;



import net.egork.collections.comparators.IntComparator;
import net.egork.collections.heap.Heap;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] p = new int[count];
		long[] exponent = new long[count];
		for (int i = 0; i < count; i++) {
			p[i] = in.readInt();
			exponent[i] = in.readLong();
		}
		long iterations = in.readLong();
		int[] primes = IntegerUtils.generatePrimes(1000000);
		int[] divisorTable = IntegerUtils.generateDivisorTable(1000000);
		int[] toIndex = new int[1000000];
		for (int i = 0; i < primes.length; i++)
			toIndex[primes[i]] = i;
		long[] base = new long[primes.length];
		long[] delta = new long[primes.length];
		long[] last = new long[primes.length];
		int interesting = -1;
		for (int i = 0; i < count; i++)
			base[toIndex[p[i]]] += exponent[i];
		long current = 0;
		while (current < iterations) {
			Arrays.fill(delta, 0);
			for (int i = 0; i < primes.length; i++) {
				if (base[i] != 0) {
					delta[i]--;
					int copy = primes[i] - 1;
					while (copy != 1) {
						delta[toIndex[divisorTable[copy]]]++;
						copy /= divisorTable[copy];
					}
				}
			}
			current++;
			boolean found = false;
			for (int i = 0; i < primes.length; i++) {
				if (base[i] == 0 && delta[i] > 0)
					found = true;
				base[i] += delta[i];
				last[i] = current;
				delta[i] = 0;
			}
			if (!found)
				break;
		}
		if (current != iterations) {
			Arrays.fill(delta, 0);
			for (int i = 0; i < primes.length; i++) {
				if (base[i] != 0) {
					delta[i]--;
					int copy = primes[i] - 1;
					while (copy != 1) {
						delta[toIndex[divisorTable[copy]]]++;
						copy /= divisorTable[copy];
					}
				}
			}
			final long[] ends = new long[primes.length];
			Arrays.fill(last, current);
			Heap heap = new Heap(new IntComparator() {
				public int compare(int first, int second) {
					return Long.compare(ends[first], ends[second]);
				}
			}, primes.length);
			for (int i = 0; i < primes.length; i++) {
				if (delta[i] < 0) {
					ends[i] = current + base[i];
					heap.add(i);
				}
			}
			while (!heap.isEmpty()) {
				int at = heap.poll();
				if (ends[at] >= iterations)
					break;
				base[at] = 0;
				delta[at] = 0;
				last[at] = ends[at];
				int copy = primes[at] - 1;
				while (copy != 1) {
					int cAt = toIndex[divisorTable[copy]];
					base[cAt] += (ends[at] - last[cAt]) * delta[cAt];
					last[cAt] = ends[at];
					delta[cAt]--;
					if (delta[cAt] == -1) {
						ends[cAt] = ends[at] + base[cAt];
						heap.add(cAt);
					}
					copy /= primes[cAt];
				}
			}
			for (int i = 0; i < primes.length; i++)
				base[i] += delta[i] * (iterations - last[i]);
		}
		int total = 0;
		for (int i = 0; i < primes.length; i++) {
			if (base[i] != 0)
				total++;
		}
		out.printLine(total);
		for (int i = 0; i < primes.length; i++) {
			if (base[i] != 0)
				out.printLine(primes[i], base[i]);
		}
    }
}
