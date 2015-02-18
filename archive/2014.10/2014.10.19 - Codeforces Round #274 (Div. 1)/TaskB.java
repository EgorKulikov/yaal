package net.egork;

import net.egork.collections.intcollection.IntArray;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int length = in.readInt();
		int small = in.readInt();
		int large = in.readInt();
		int[] marks = IOUtils.readIntArray(in, count);
		int[] answer = new int[]{small, large};
		boolean hasLarge = false;
		boolean hasSmall = false;
		for (int i : marks) {
			if (Arrays.binarySearch(marks, i - small) >= 0) {
				hasSmall = true;
			}
			if (Arrays.binarySearch(marks, i - large) >= 0) {
				hasLarge = true;
			}
			if (answer.length == 2) {
				if (Arrays.binarySearch(marks, i - large - small) >= 0) {
					answer = new int[]{i - small};
				} else if ((i + small <= length || i >= large) && Arrays.binarySearch(marks, i - large + small) >= 0) {
					answer = new int[]{i + small <= length ? i + small : i - large};
				}
			}
		}
		if (hasSmall && hasLarge) {
			answer = new int[0];
		} else if (hasSmall) {
			answer = new int[]{large};
		} else if (hasLarge) {
			answer = new int[]{small};
		}
		out.printLine(answer.length);
		out.printLine(answer);
    }
}
