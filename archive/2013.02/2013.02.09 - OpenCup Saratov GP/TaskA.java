package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		int[] max = IOUtils.readIntArray(in, count);
		int[] start = IOUtils.readIntArray(in, count);
//		in = new InputReader(System.in);
//		out = new OutputWriter(System.out);
		for (int i = 0; i < count; i++) {
			if ((max[i] & 1) == 1) {
				out.printLine("Alice");
				while (true) {
					out.printLine(i + 1, (start[i] & 1) == 1 ? '-' : '+');
					start[i] ^= 1;
					out.flush();
					int row = in.readInt() - 1;
					char type = in.readCharacter();
					if (type == '?')
						return;
					if (type == '+')
						start[row]++;
					else
						start[row]--;
				}
			}
		}
		IntList badTypes = new IntArrayList();
		for (int i = 0; i < count; i++) {
			if ((start[i] & 1) == 1)
				badTypes.add(i);
		}
		int[] current = start.clone();
		if ((badTypes.size() & 1) == 1) {
			out.printLine("Alice");
			while (true) {
				for (int i = 0; i < count; i++) {
					if (current[i] != max[i]) {
						out.printLine(i + 1, (current[i] & 1) == 0 ? '+' : '-');
						current[i] ^= 1;
						out.flush();
						break;
					}
				}
				int row = in.readInt() - 1;
				char type = in.readCharacter();
				if (type == '?')
					return;
				if (type == '+')
					current[row]++;
				else
					current[row]--;
			}
		}
		out.printLine("Bob");
		out.flush();
		int[] bad = badTypes.toArray();
		int[] other = new int[count];
		Arrays.fill(other, -1);
		for (int i = 0; i < bad.length; i++)
			other[bad[i]] = bad[i ^ 1];
		while (true) {
			int row = in.readInt() - 1;
			char type = in.readCharacter();
			if (type == '?')
				return;
			if (type == '+')
				current[row]++;
			else
				current[row]--;
			boolean move = false;
			for (int i = 0; i < count; i++) {
				if (current[i] == start[i])
					continue;
				if (other[i] == -1 || Math.abs(current[i] - start[i]) > 1) {
					if (current[i] > start[i]) {
						out.printLine(i + 1, (current[i] & 1) == 1 ? '+' : '-');
						current[i]--;
						current[i] ^= 1;
						current[i]++;
					} else {
						out.printLine(i + 1, (current[i] & 1) == 1 ? '-' : '+');
						current[i] ^= 1;
					}
					move = true;
					out.flush();
					break;
				}
			}
			if (move)
				continue;
			for (int i = 0; i < count; i++) {
				if (current[i] != start[i] || other[i] != -1 && current[other[i]] != start[other[i]]) {
					int d1 = current[i] - start[i];
					int d2 = current[other[i]] - start[other[i]];
					if (d1 != 1 && d2 == 1) {
						out.printLine(i + 1, '+');
						current[i]++;
					} else if (d1 == 1 && d2 != -1) {
						out.printLine(other[i] + 1, '-');
						current[other[i]]--;
					} else if (d2 == -1 && d1 != -1) {
						out.printLine(i + 1, '-');
						current[i]--;
					} else {
						out.printLine(other[i] + 1, '+');
						current[other[i]]++;
					}
					out.flush();
					break;
				}
			}
		}
    }
}
