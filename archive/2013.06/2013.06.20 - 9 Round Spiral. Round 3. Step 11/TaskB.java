package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.misc.Factory;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int storageCount = in.readInt();
		int total = in.readInt();
		IntList[] storages = new IntList[storageCount];
		ListUtils.fill(Array.wrap(storages), new Factory<IntList>() {
			public IntList create() {
				return new IntArrayList();
			}
		});
		int answer = 0;
		int current = 0;
		for (int i = 0; i < count; i++) {
			if (in.readCharacter() == '+') {
				current++;
				if (total < current) {
					out.printLine("Error");
					return;
				}
				answer = Math.max(answer, current);
				int index = in.readInt() - 1;
				storages[index].add(in.readInt());
			} else {
				current--;
				int index = in.readInt() - 1;
				int expected = in.readInt();
				if (storages[index].size() == 0 || storages[index].back() != expected) {
					out.printLine("Error");
					return;
				}
				storages[index].popBack();
			}
		}
		if (current != 0) {
			out.printLine("Error");
			return;
		}
		out.printLine(answer);
    }
}
