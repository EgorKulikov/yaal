package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LuckyDays {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int first = in.readInt();
		int second = in.readInt();
		int currentMultiplier = in.readInt();
		int lastMultiplier = in.readInt();
		int addition = in.readInt();
		int module = in.readInt();
		int luckyNumber = in.readInt();
		List<Integer> luckyIndices = new ArrayList<Integer>();
		if (currentMultiplier == 0 && lastMultiplier == 0) {
			int queryCount = in.readInt();
			for (int i = 0; i < queryCount; i++) {
				long from = in.readLong();
				long to = in.readLong();
				long result = 0;
				if (from == 1 && first == luckyNumber)
					result++;
				if (from <= 2 && to >= 2 && second == luckyNumber)
					result++;
				if (luckyNumber == addition)
					result += Math.max(0, to - Math.max(from, 3) + 1);
				out.printLine(result);
			}
			return;
		}
		if (lastMultiplier == 0) {
			if (second == luckyNumber)
				luckyIndices.add(0);
			int current = second;
			int period = 1;
			while (true) {
				current = (current * currentMultiplier + addition) % module;
				if (current == second)
					break;
				if (current == luckyNumber)
					luckyIndices.add(period);
				period++;
			}
			int queryCount = in.readInt();
			for (int i = 0; i < queryCount; i++) {
				long from = in.readLong();
				long to = in.readLong();
				long result = 0;
				if (from == 1 && first == luckyNumber)
					result++;
				from -= 2;
				to--;
				result += to / period * luckyIndices.size();
				result -= from / period * luckyIndices.size();
				result -= count(from % period, luckyIndices);
				result += count(to % period, luckyIndices);
				out.printLine(result);
			}
			return;
		}
		int current = second;
		int last = first;
		int period = 1;
		if (first == luckyNumber)
			luckyIndices.add(0);
		int[] currentValues = new int[module];
		int[] lastValues = new int[module];
		for (int i = 0; i < module; i++) {
			currentValues[i] = (i * currentMultiplier + addition) % module;
			lastValues[i] = (i * lastMultiplier) % module;
		}
		int[] sum = new int[2 * module];
		for (int i = 0; i < 2 * module; i++)
			sum[i] = i % module;
		while (true) {
			int next = sum[currentValues[current] + lastValues[last]];
			last = current;
			current = next;
			if (last == first && current == second)
				break;
			if (last == luckyNumber)
				luckyIndices.add(period);
			period++;
		}
//		System.err.println(period);
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			long from = in.readLong();
			long to = in.readLong();
			long result = 0;
			from--;
			result += to / period * luckyIndices.size();
			result -= from / period * luckyIndices.size();
			result -= count(from % period, luckyIndices);
			result += count(to % period, luckyIndices);
			out.printLine(result);
		}
	}

	private long count(long upTo, List<Integer> luckyIndices) {
		int index = Collections.binarySearch(luckyIndices, (int)upTo);
		if (index >= 0)
			return index;
		else
			return -index - 1;
	}
}
