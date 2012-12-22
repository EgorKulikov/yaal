package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AfternoonTea {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readInt();
		String s = in.readString();
		s = s.substring(0, s.length() - 1);
		if (s.isEmpty()) {
			out.printLine("HM");
		} else {
			int cntH = 0;
			int cntM = 0;
			for (int i = 0; i < s.length(); ++i)
				if (s.charAt(i) == 'H') ++cntH; else ++cntM;
			if (cntH > cntM)
				out.printLine("H");
			else if (cntM > cntH)
				out.printLine("M");
			else if (s.charAt(s.length() - 1) == 'H')
				out.printLine("M");
			else
				out.printLine("H");
		}
	}
}
