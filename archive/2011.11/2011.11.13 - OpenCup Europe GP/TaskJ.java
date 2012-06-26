package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskJ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int divisorCount = 0;
		for (int i = 1; i * i <= count; i++) {
			if (count % i == 0) {
				divisorCount++;
				if (i * i != count)
					divisorCount++;
			}
		}
		int[] divisors = new int[divisorCount];
		for (int i = 1; i * i <= count; i++) {
			if (count % i == 0) {
				divisors[--divisorCount] = i;
				if (i * i != count)
					divisors[--divisorCount] = count / i;
			}
		}
		Arrays.sort(divisors);
		int[] gcd = new int[count + 1];
		for (int i : divisors) {
			for (int j = 0; j <= count; j += i)
				gcd[j] = i;
		}
		int[] subTrees = new int[count + 1];
		int[] parent = new int[count];
		for (int i = 1; i < count; i++) {
			parent[i] = in.readInt() - 1;
		}
		int[] size = new int[count];
		Arrays.fill(size, 1);
		for (int i = count - 1; i > 0; i--)
			size[parent[i]] += size[i];
		for (int i : size)
			subTrees[gcd[i]]++;
		List<Integer> result = new ArrayList<Integer>();
		for (int i : divisors) {
			int totalCount = 0;
			for (int j : divisors) {
				if (j % i == 0)
					totalCount += subTrees[j];
			}
			if (totalCount == count / i)
				result.add(count / i);
		}
		Collections.sort(result);
		out.printLine(result.toArray());
	}
}
