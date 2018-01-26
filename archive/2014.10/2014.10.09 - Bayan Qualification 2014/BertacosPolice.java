package net.egork;

import net.egork.string.StringUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class BertacosPolice {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(StringUtils.reverse(in.readString()));
    }
}
