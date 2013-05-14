package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int manCount = in.readInt();
		int armorCount = in.readInt();
		int x = in.readInt();
		int y = in.readInt();
		int[] men = IOUtils.readIntArray(in, manCount);
		int[] armor = IOUtils.readIntArray(in, armorCount);
		List<Pair<Integer, Integer>> answer = new ArrayList<Pair<Integer, Integer>>();
		Integer[] manOrder = ListUtils.order(Array.wrap(men));
		Integer[] armorOrder = ListUtils.order(Array.wrap(armor));
		int armorIndex = 0;
		for (int i : manOrder) {
			while (armorIndex < armorCount && men[i] - x > armor[armorOrder[armorIndex]])
				armorIndex++;
			if (armorIndex == armorCount)
				break;
			if (men[i] + y < armor[armorOrder[armorIndex]])
				continue;
			answer.add(Pair.makePair(i + 1, armorOrder[armorIndex++] + 1));
		}
		out.printLine(answer.size());
		for (Pair<Integer, Integer> pair : answer) {
			out.printLine(pair.first, pair.second);
		}
	}
}
