package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.StringWrapper;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		char digit = in.readCharacter();
		out.printLine(CollectionUtils.count(StringWrapper.wrap(s), digit));
	}
}
