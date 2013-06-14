package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Interactive {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int[] permutation = new int[size];
		for (int i = 0; i < size; i++)
			permutation[i] = i + 1;
		print(out, permutation);
		int current = in.readInt();
		Random rand = new Random(239);
		Set<Integer> set = new HashSet<Integer>();
		Set<Integer> forbidden = new HashSet<Integer>();
		while (current != size) {
			int first = rand.nextInt(size);
			while (forbidden.contains(first))
				first = rand.nextInt(size);
			int second = rand.nextInt(size);
			int key = first * size + second;
			while (first == second || set.contains(key)) {
				second = rand.nextInt(size);
				key = first * size + second;
			}
			set.add(key);
			int[] newPermutation = Arrays.copyOf(permutation, size);
			if (second > first) {
				System.arraycopy(permutation, first + 1, newPermutation, first, second - first);
				newPermutation[second] = permutation[first];
			} else {
				System.arraycopy(permutation, second, newPermutation, second + 1, first - second);
				newPermutation[second] = permutation[first];
			}
			print(out, newPermutation);
			int next = in.readInt();
			if (next < current)
				forbidden.add(first);
			else if (next == current)
				set.add(key);
			else {
				permutation = newPermutation;
				current = next;
				set.clear();
				forbidden.clear();
			}
		}
	}

	private void print(OutputWriter out, int[] permutation) {
		out.print(permutation[0]);
		for (int i = 1; i < permutation.length; i++)
			out.print("", permutation[i]);
		out.printLine();
		out.flush();
	}
}
