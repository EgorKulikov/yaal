package on2013_02.on2013_02_17_February_Cook_Off_2013.End_Of_The_World_2;



import net.egork.collections.set.EHashSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Set;

public class EndOfTheWorld {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long max = 3141L * 3141L * 3141L * 3141L * 3141L;
		Set<Long> interesting = new EHashSet<Long>();
		for (long i = 2; i * i * i <= max; i++) {
			long current = i * i * i;
			long ceil = max / i;
			while (true) {
				if (!isSquare(current))
					interesting.add(current);
				if (ceil < current)
					break;
				current *= i;
			}
		}
		long[] array = new long[interesting.size()];
		int k = 0;
		for (long l : interesting)
			array[k++] = l;
		Arrays.sort(array);
		int count = in.readInt();
		long[] answer = new long[count];
		for (int i = 0; i < count; i++) {
			long current = in.readLong();
			if (current == 0)
				answer[i] = 2;
			else if (current == 1)
				answer[i] = 1;
			else if (isSquare(current) || interesting.contains(current)) {
				answer[i] = root(current) - 1;
				int index = Arrays.binarySearch(array, current);
				if (index < 0)
					index = -index - 1;
				else
					index++;
				answer[i] += index;
			} else {
				long delta = root(current) - 1 - Arrays.binarySearch(array, current) - 1;
				current -= delta;
				current++;
				for (int j = 1; ; j++) {
					answer[i]++;
					if ((1L << j) == current)
						break;
					if ((1L << j) > current) {
						answer[i] *= 2;
						answer[i]--;
						answer[i] -= Long.bitCount(Long.lowestOneBit(current) - 1);
						break;
					}
				}
			}
		}
		out.printLine(answer);
    }

	long root(long l) {
		long root = (long) Math.sqrt(l);
		while (root * root > l)
			root--;
		while ((root + 1) * (root + 1) <= l)
			root++;
		return root;
	}

	boolean isSquare(long l) {
		long root = root(l);
		return root * root == l;
	}
}
