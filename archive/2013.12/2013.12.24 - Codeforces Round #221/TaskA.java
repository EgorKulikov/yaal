package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] number = in.readString().toCharArray();
		boolean[] notSeen = new boolean[10];
		notSeen[1] = notSeen[6] = notSeen[8] = notSeen[9] = true;
		StringBuilder answer = new StringBuilder();
		int remainder = 0;
		for (char c : number) {
			if (notSeen[c - '0'])
				notSeen[c - '0'] = false;
			else {
				answer.append(c);
				remainder *= 10;
				remainder += c - '0';
				remainder %= 7;
			}
		}
		IntList list = new IntArrayList();
		list.add(1);
		list.add(6);
		list.add(8);
		list.add(9);
		int multiplier = (int) IntegerUtils.power(10, answer.length(), 7);
		do {
			int x = 0;
			for (int i : list.toArray()) {
				x *= 10;
				x += i;
				x %= 7;
			}
			if ((x * multiplier + remainder) % 7 == 0) {
				for (int i : list.toArray())
					out.print(i);
				out.printLine(answer);
				return;
			}
		} while (list.nextPermutation());
		throw new RuntimeException();
    }
}
