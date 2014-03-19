package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int mod = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int[] value = new int[count];
		int[] qty = new int[count + 1];
		int size = 0;
		for (int i : array) {
			int at = -Arrays.binarySearch(value, 0, size, 2 * i) - 1;
			value[at] = 2 * i + 1;
			if (at == size)
				size++;
			qty[at + 1]++;
		}
		qty[0] = 1;
		int[][] elements = new int[count + 1][];
		int[][] ways = new int[count + 1][];
		int[] occupied = new int[count + 1];
		int[] totalWays = new int[count + 1];
		for (int i = 0; i <= count; i++) {
			elements[i] = new int[qty[i]];
			ways[i] = new int[qty[i]];
		}
		occupied[0] = 1;
		elements[0][0] = 1;
		ways[0][0] = 1 % mod;
		totalWays[0] = 1 % mod;
		size = 0;
		for (int i : array) {
			int at = -Arrays.binarySearch(value, 0, size, 2 * i) - 1;
			value[at] = 2 * i + 1;
			if (at == size)
				size++;
			qty[at + 1]++;
			elements[at + 1][occupied[at + 1]] = -2 * i + 1;
			int levelAt = -Arrays.binarySearch(elements[at], 0, occupied[at], -2 * i) - 2;
			int toAdd;
			if (levelAt == -1)
				toAdd = totalWays[at];
			else
				toAdd = totalWays[at] - ways[at][levelAt];
			if (toAdd < 0)
				toAdd += mod;
			totalWays[at + 1] += toAdd;
			if (totalWays[at + 1] >= mod)
				totalWays[at + 1] -= mod;
			ways[at + 1][occupied[at + 1]] = toAdd;
			if (occupied[at + 1] != 0)
				ways[at + 1][occupied[at + 1]] += ways[at + 1][occupied[at + 1] - 1];
			if (ways[at + 1][occupied[at + 1]] >= mod)
				ways[at + 1][occupied[at + 1]] -= mod;
			occupied[at + 1]++;
		}
		out.printLine(totalWays[size]);
    }
}
