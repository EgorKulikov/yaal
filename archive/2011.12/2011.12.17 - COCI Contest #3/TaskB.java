package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.comparators.ReverseComparator;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.collections.set.MultiSet;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int total = in.readInt();
		int partyCount = in.readInt();
		String[] name = new String[partyCount];
		int[] votes = new int[partyCount];
		for (int i = 0; i < partyCount; i++) {
			name[i] = in.readString();
			votes[i] = in.readInt();
		}
		List<Pair<Rational, String>> candidates = new ArrayList<Pair<Rational, String>>();
		for (int i = 0; i < partyCount; i++) {
			if (total / 20 > votes[i])
				continue;
			for (int j = 1; j <= 14; j++)
				candidates.add(Pair.makePair(new Rational(votes[i], j), name[i]));
		}
		if (candidates.size() == 0)
			return;
		Collections.sort(candidates, new ReverseComparator<Pair<Rational, String>>());
		MultiSet<String> representatives = new MultiSet<String>();
		for (int i = 0; i < 14; i++)
			representatives.add(candidates.get(i).second);
		String[] representativeNames = new String[representatives.entryCount()];
		int[] representativeCount = new int[representatives.entryCount()];
		int index = 0;
		for (Map.Entry<String, Integer> entry : representatives.getUnderlying().entrySet()) {
			representativeNames[index] = entry.getKey();
			representativeCount[index++] = entry.getValue();
		}
		Integer[] order = ListUtils.order(Array.wrap(representativeNames));
		for (int i : order)
			out.printLine(representativeNames[i], representativeCount[i]);
	}
}
