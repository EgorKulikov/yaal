package net.egork;

import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int operationCount = in.readInt();
		char[] digits = in.readString().toCharArray();
		for (int i = 1; i < count && operationCount > 0; i++) {
			if (digits[i - 1] == '4' && digits[i] == '7') {
				if (i % 2 == 0) {
					digits[i - 1] = '7';
					operationCount--;
					if (digits[i - 2] == '4') {
						if (operationCount % 2 == 1)
							digits[i - 1] = '4';
						operationCount = 0;
					}
				} else {
					digits[i] = '4';
					operationCount--;
					if (i + 1 < count && digits[i + 1] == '7') {
						if (operationCount % 2 == 1)
							digits[i] = '7';
						operationCount = 0;
					}
				}
			}
		}
		out.println(digits);
	}
}
