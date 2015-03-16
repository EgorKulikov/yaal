package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DevuAndHisClass {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int type = in.readInt();
		char[] line = in.readString().toCharArray();
		int boys = ArrayUtils.count(line, 'B');
		int girls = line.length - boys;
		if (Math.abs(boys - girls) > 1) {
			out.printLine(-1);
			return;
		}
		long answer = Long.MAX_VALUE;
		for (char first = 'B'; first <= 'G'; first += 'G' - 'B') {
			if (first == 'B' && girls > boys || first == 'G' && boys > girls) {
				continue;
			}
			long current = 0;
			if (type <= 1) {
				IntList even = new IntArrayList();
				IntList odd = new IntArrayList();
				for (int i = 0; i < line.length; i += 2) {
					if (line[i] != first) {
						even.add(i);
					}
				}
				for (int i = 1; i < line.length; i += 2) {
					if (line[i] == first) {
						odd.add(i);
					}
				}
				if (type == 0) {
					current = even.size();
				} else {
					for (int i = 0; i < even.size(); i++) {
						current += Math.abs(even.get(i) - odd.get(i));
					}
				}
			} else {
				char[] copy = line.clone();
				int currentEven = 0;
				int currentOdd = 0;
				for (int i = 0; i < line.length; i++) {
					currentEven = Math.max(currentEven, i);
					currentOdd = Math.max(currentOdd, i);
					if ((i & 1) == 0) {
						if (copy[i] != first) {
							while (copy[currentEven] != first) {
								currentEven++;
							}
							current += currentEven - i;
							copy[i] = first;
							copy[currentEven] = (char) ('B' + 'G' - first);
						}
					} else {
						if (copy[i] == first) {
							while (copy[currentOdd] == first) {
								currentOdd++;
							}
							current += currentOdd - i;
							copy[i] = (char) ('B' + 'G' - first);
							copy[currentOdd] = first;
						}
					}
				}
			}
			answer = Math.min(answer, current);
		}
		out.printLine(answer);
    }
}
