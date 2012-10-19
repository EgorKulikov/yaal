package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int firstCount = in.readInt();
		int[] firstDeck = IOUtils.readIntArray(in, firstCount);
		int secondCount = in.readInt();
		int[] secondDeck = IOUtils.readIntArray(in, secondCount);
		List<Integer> first = solve(firstDeck, secondDeck);
		List<Integer> second = solve(secondDeck, firstDeck);
		int current;
		if (first.size() > second.size()) {
			current = secondDeck[0];
			first = second;
		} else {
			current = firstDeck[0];
		}
		int i = 0;
		int j = 0;
		int[] answer = new int[firstCount + secondCount];
		while (i < firstCount || j < secondCount) {
			while (i < firstCount && firstDeck[i] == current) {
				answer[i + j] = i + 1;
				i++;
			}
			while (j < secondCount && secondDeck[j] == current) {
				answer[i + j] = firstCount + j + 1;
				j++;
			}
			current = 1 - current;
		}
		out.printLine(Array.wrap(answer).toArray());
		out.printLine(first.size());
		out.printLine(first.toArray());
	}

	private List<Integer> solve(int[] firstDeck, int[] secondDeck) {
		int current = firstDeck[0];
		List<Integer> same = new ArrayList<Integer>();
		int i = 0;
		int j = 0;
		int firstCount = firstDeck.length;
		int secondCount = secondDeck.length;
		while (i < firstCount || j < secondCount) {
			int curMove = 0;
			while (i < firstCount && firstDeck[i] == current) {
				curMove++;
				i++;
			}
			while (j < secondCount && secondDeck[j] == current) {
				curMove++;
				j++;
			}
			current = 1 - current;
			same.add(curMove);
		}
		List<Integer> result = new ArrayList<Integer>();
		int sum = 0;
		for (int k : same) {
			sum += k;
			if (sum != firstCount + secondCount || current == 0) {
				result.add(sum);
			}
		}
		return result;
	}
}
