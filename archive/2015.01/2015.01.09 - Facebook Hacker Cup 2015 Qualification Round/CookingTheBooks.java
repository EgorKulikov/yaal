package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CookingTheBooks {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] number = in.readString().toCharArray();
		char[] first = number.clone();
		for (int i = 0; i < number.length; i++) {
			char min = number[i];
			int at = i;
			for (int j = number.length - 1; j > i; j--) {
				if (number[j] < min && (number[j] != '0' || i > 0)) {
					min = number[j];
					at = j;
				}
			}
			if (at != i) {
				first[at] = number[i];
				first[i] = number[at];
				break;
			}
		}
		char[] second = number.clone();
		for (int i = 0; i < number.length; i++) {
			char max = number[i];
			int at = i;
			for (int j = number.length - 1; j > i; j--) {
				if (number[j] > max) {
					max = number[j];
					at = j;
				}
			}
			if (at != i) {
				second[at] = number[i];
				second[i] = number[at];
				break;
			}
		}
		out.printLine("Case #" + testNumber + ":", new String(first), new String(second));
    }
}
