package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.HashSet;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(new HashSet<Integer>(Array.wrap(IOUtils.readIntArray(in, in.readInt()))).size());
	}
}
