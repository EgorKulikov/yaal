package net.egork;

import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheMirrorOfGaladriel {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		if (s.equals(StringUtils.reverse(s)))
			out.printLine("YES");
		else
			out.printLine("NO");
    }
}
