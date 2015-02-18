package net.egork;

import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BertacosPolice {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(StringUtils.reverse(in.readString()));
    }
}
