package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;

public class FindTheMin {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int known = in.readInt();
		int a = in.readInt();
		long b = in.readInt();
		int c = in.readInt();
		int mod = in.readInt();
		int[] was = new int[known + 1];
		Arrays.fill(was, -1);
		int current = a;
		for (int i = 0; i < known; i++) {
			if (current <= known)
				was[current] = i;
			current = (int) ((current * b + c) % mod);
		}
		int[] toAdd = new int[known + 1];
		Arrays.fill(toAdd, -1);
		for (int i = 0; i <= known; i++) {
			if (was[i] != -1)
				toAdd[was[i]] = i;
		}
		NavigableSet<Integer> free = new TreeSet<Integer>();
		for (int i = 0; i <= known; i++) {
			if (was[i] == -1)
				free.add(i);
		}
		int[] answer = new int[known + 1];
		for (int i = 0; i <= known; i++) {
			answer[i] = free.pollFirst();
			if (toAdd[i] != -1)
				free.add(toAdd[i]);
		}
    	out.printLine("Case #" + testNumber + ":", answer[(size - known - 1) % (known + 1)]);
    }
}
