package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] s = in.readString().toCharArray();
		int count = s.length;
		int current = 1;
		while (count > 0 && s[0] == s[count - 1]) {
			count--;
			current++;
		}
		IntList sizes = new IntArrayList();
		for (int i = 1; i < count; i++) {
			if (s[i] != s[i - 1]) {
				sizes.add(current);
				current = 1;
			} else
				current++;
		}
		sizes.add(current);
		int answer = 0;
		int[] array = sizes.toArray();
		boolean skip = false;
		for (int i : array) {
			if (skip) {
				skip = false;
				continue;
			}
			if (i == 1) {
				skip = true;
				answer++;
			} else {
				answer++;
			}
		}
		if (skip)
			answer--;
		skip = false;
		int candidate = 0;
		array = Arrays.copyOfRange(array, 1, array.length);
		for (int i : array) {
			if (skip) {
				skip = false;
				continue;
			}
			if (i == 1) {
				skip = true;
				candidate++;
			} else {
				candidate++;
			}
		}
		if (skip)
			candidate--;
		answer = Math.max(answer, candidate);
		out.printLine(answer);
    }
}
