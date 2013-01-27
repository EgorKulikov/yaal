package net.egork;

import net.egork.collections.map.Counter;
import net.egork.collections.sequence.ListUtils;
import net.egork.string.StringUtils;
import net.egork.utils.io.OutputWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FleaCircus {
	long[] odd;
	private static final long MOD = (long) (1e9 + 9);

	public int countArrangements(String[] afterFourClicks) {
		String all = StringUtils.unite(afterFourClicks);
		String[] tokens = all.split(" ");
		int[] permutation = new int[tokens.length];
		for (int i = 0; i < tokens.length; i++)
			permutation[i] = Integer.parseInt(tokens[i]);
		boolean[] visited = new boolean[permutation.length];
		int[] count = new int[permutation.length + 1];
		for (int i = 0; i < permutation.length; i++) {
			if (visited[i])
				continue;
			int length = 0;
			int current = i;
			do {
				current = permutation[current];
				visited[current] = true;
				length++;
			} while (current != i);
			count[length]++;
		}
		odd = new long[permutation.length + 1];
		long result = 1;
		for (int i = 1; i <= permutation.length; i++) {
			if (i % 2 == 1) {
				Arrays.fill(odd, -1);
				odd[0] = 1;
				result = result * odd(count[i], i) % MOD;
			} else
				result = result * even(count[i], i) % MOD;
		}
		return (int) result;
	}

	private long even(int count, long perCycle) {
		if ((count & 3) != 0)
			return 0;
		if (count == 0)
			return 1;
		return even(count - 4, perCycle) * ((long)(count - 1) * (count - 2) * (count - 3) % MOD) % MOD * perCycle * perCycle % MOD * perCycle % MOD;
	}

	private long odd(int count, long perCycle) {
		if (odd[count] != -1)
			return odd[count];
		odd[count] = odd(count - 1, perCycle);
		if (count >= 2)
			odd[count] += odd(count - 2, perCycle) * (count - 1) * perCycle;
		if (count >= 4)
			odd[count] += odd(count - 4, perCycle) * ((long)(count - 1) * (count - 2) * (count - 3) % MOD) % MOD * perCycle * perCycle % MOD * perCycle;
		return odd[count] %= MOD;
	}

	public static void main(String[] args) {
		for (int n = 9; n <= 9; n++) {
		List<Integer> permutation = new ArrayList<Integer>(n);
		for (int i = 0; i < n; i++)
			permutation.add(i);
		Counter<List<Integer>> answer = new Counter<List<Integer>>();
		int total = 0;
			List<Integer> qCopy = null;
		do {
			total++;
			List<Integer> quadrupled = new ArrayList<Integer>(n);
			for (int i = 0; i < n; i++)
				quadrupled.add(permutation.get(permutation.get(permutation.get(permutation.get(i)))));
			answer.add(quadrupled);
			if (permutation.equals(Arrays.asList(2, 5, 3, 4, 1, 6, 0, 7, 8))) {
				qCopy = Collections.unmodifiableList(quadrupled);
			}

		} while (ListUtils.nextPermutation(permutation));
		Collections.reverse(permutation);
		FleaCircus task = new FleaCircus();
		do {
			StringWriter sw = new StringWriter();
			OutputWriter out = new OutputWriter(sw);
			out.print(permutation.toArray());
			if (task.countArrangements(new String[]{sw.toString()}) != answer.get(permutation)) {
				task.countArrangements(new String[]{sw.toString()});
				throw new RuntimeException();
			}
		} while (ListUtils.nextPermutation(permutation));
		}
	}
}

