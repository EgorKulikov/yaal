package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class Sandwiches {
	static long[] power = new long[1500];

	static {
		power[0] = 1;
		for (int i = 1; i < 1500; i++)
			power[i] = power[i - 1] * 1507;
	}

	public void solve(int testNumber, final InputReader in, OutputWriter out) {
		final int count = in.readInt();
		final int[][] tastiness = new int[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				tastiness[i][j] = -in.readInt();
			}
		}
		for (int i = 0; i < count; i++) {
			Arrays.sort(tastiness[i]);
			for (int j = 0; j < count; j++)
				tastiness[i][j] = -tastiness[i][j];
		}
		if (count == 1) {
			out.printLine(tastiness[0][0]);
			return;
		}
		final int[][] base = new int[count][count];
		final int[] value = new int[count];
		final long[] baseHash = new long[count];
		NavigableSet<Integer> queue = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				int baseIndex1 = o1 & 2047;
				int shiftIndex1 = o1 >> 11;
				int baseIndex2 = o2 & 2047;
				int shiftIndex2 = o2 >> 11;
				long value1 = value[baseIndex1] - tastiness[shiftIndex1][base[baseIndex1][shiftIndex1]] + tastiness[shiftIndex1][base[baseIndex1][shiftIndex1] + 1];
				long value2 = value[baseIndex2] - tastiness[shiftIndex2][base[baseIndex2][shiftIndex2]] + tastiness[shiftIndex2][base[baseIndex2][shiftIndex2] + 1];
				int value = Long.signum(value2 - value1);
				if (value != 0)
					return value;
				long hash1 = baseHash[baseIndex1] + power[shiftIndex1];
				long hash2 = baseHash[baseIndex2] + power[shiftIndex2];
				if (hash1 < hash2)
					return -1;
				else if (hash1 > hash2)
					return 1;
				return 0;
			}
		}) {
			@Override
			public boolean add(Integer integer) {
				super.add(integer);
				if (size() > count)
					pollLast();
				return true;
			}
		};
		for (int i = 0; i < count; i++) {
			value[0] += tastiness[i][0];
		}
		for (int i = 0; i < count; i++)
			queue.add(i << 11);
		Set<Long> was = new HashSet<Long>();
		was.add(0L);
		for (int i = 1; i < count; i++) {
			int index, baseIndex, shiftIndex;
			long hash;
			index = queue.pollFirst();
			baseIndex = index & 2047;
			shiftIndex = index >> 11;
			hash = baseHash[baseIndex] + power[shiftIndex];
			baseHash[i] = hash;
			was.add(hash);
			value[i] = value[baseIndex] - tastiness[shiftIndex][base[baseIndex][shiftIndex]] + tastiness[shiftIndex][base[baseIndex][shiftIndex] + 1];
			if (i == count - 1)
				break;
			System.arraycopy(base[baseIndex], 0, base[i], 0, count);
			base[i][shiftIndex]++;
			for (int j = 0; j < count; j++) {
				long curHash = hash + power[j];
				if (was.contains(curHash))
					continue;
				queue.add((j << 11) + i);
			}
		}
		out.print(value[0]);
		for (int i = 1; i < count; i++)
			out.print(" " + value[i]);
		out.printLine();
	}
}
