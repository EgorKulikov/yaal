package net.egork;

import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Genetic {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String generated = in.readString();
		String original = in.readString();
		out.printLine(StringUtils.contains(generated, original) && StringUtils.count(generated, 'T') > 0 ? 'Y' : 'N');
    }
}
