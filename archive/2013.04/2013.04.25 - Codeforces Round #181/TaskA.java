package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		IntList negative = new IntArrayList();
		IntList positive = new IntArrayList();
		IntList zero = new IntArrayList();
		IntList coupleMoreNegatives = new IntArrayList();
		for (int i : numbers) {
			if (i > 0)
				positive.add(i);
			if (i == 0)
				zero.add(i);
			if (i < 0) {
				if (negative.size() == 0)
					negative.add(i);
				else if (coupleMoreNegatives.size() < 2)
					coupleMoreNegatives.add(i);
				else
					zero.add(i);
			}
		}
		if (coupleMoreNegatives.size() == 2)
			positive.addAll(coupleMoreNegatives);
		else
			zero.addAll(coupleMoreNegatives);
		out.print(negative.size() + " ");
		out.printLine(negative);
		out.print(positive.size() + " ");
		out.printLine(positive);
		out.print(zero.size() + " ");
		out.printLine(zero);
    }
}
