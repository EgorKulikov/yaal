package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String first = in.readString();
		String second = in.readString();
		if (first.length() < second.length()) {
			first = new String(ArrayUtils.createArray(second.length() - first.length(), '0')) + first;
		} else {
			second = new String(ArrayUtils.createArray(first.length() - second.length(), '0')) + second;
		}
		int more = 0;
		int carry = 0;
		for (int i = 0; i < first.length(); i++) {
			int current = (int)first.charAt(i) - second.charAt(i);
			if (more != 0) {
				if (current != -more) {
					if (more > 0) {
						out.printLine('>');
					} else {
						out.printLine('<');
					}
					return;
				}
				carry = more;
				more = 0;
				continue;
			}
			if (carry != 0) {
				if (current == carry) {
					if (carry > 0) {
						out.printLine('>');
					} else {
						out.printLine('<');
					}
					return;
				}
			}
			more = carry + current;
			carry = 0;
		}
		if (more + carry > 0) {
			out.printLine('>');
		} else if (more + carry < 0) {
			out.printLine('<');
		} else {
			out.printLine('=');
		}
    }
}
