package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class Combos {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int pressCount = in.readInt();
		String[] combos = IOUtils.readStringArray(in, count);
		Map<String, Integer> points = new HashMap<String, Integer>();
		Map<String, String> next = new HashMap<String, String>();
		for (String base : combos) {
			for (int i = 0; i <= base.length(); i++) {
				String current = base.substring(0, i);
				if (points.containsKey(current))
					continue;
				int currentPoints = 0;
				for (String combo : combos) {
					if (current.endsWith(combo))
						currentPoints++;
				}
				points.put(current, currentPoints);
			}
		}
		for (String base : points.keySet()) {
			for (char c = 'A'; c <= 'C'; c++) {
				String current = base + c;
				for (int i = 0; i <= current.length(); i++) {
					if (points.containsKey(current.substring(i))) {
						next.put(current, current.substring(i));
						break;
					}
				}
			}
		}
		Map<String, Integer> index = new HashMap<String, Integer>();
		int currentIndex = 0;
		for (String base : points.keySet())
			index.put(base, currentIndex++);
		int[] arrayPoints = new int[currentIndex];
		int[][] arrayNext = new int[currentIndex][3];
		for (Map.Entry<String, Integer> entry : index.entrySet()) {
			arrayPoints[entry.getValue()] = points.get(entry.getKey());
			arrayNext[entry.getValue()][0] = index.get(next.get(entry.getKey() + 'A'));
			arrayNext[entry.getValue()][1] = index.get(next.get(entry.getKey() + 'B'));
			arrayNext[entry.getValue()][2] = index.get(next.get(entry.getKey() + 'C'));
		}
		int[][] answer = new int[pressCount + 1][currentIndex];
		ArrayUtils.fill(answer, Integer.MIN_VALUE);
		answer[0][index.get("")] = 0;
		for (int i = 0; i < pressCount; i++) {
			for (int j = 0; j < currentIndex; j++) {
				if (answer[i][j] == Integer.MIN_VALUE)
					continue;
				for (int k = 0; k < 3; k++)
					answer[i + 1][arrayNext[j][k]] = Math.max(answer[i + 1][arrayNext[j][k]], answer[i][j] +
						arrayPoints[arrayNext[j][k]]);
			}
		}
		out.printLine(CollectionUtils.maxElement(Array.wrap(answer[pressCount])));
	}
}
