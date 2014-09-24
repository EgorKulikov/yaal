package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String password = in.readString();
		boolean hasLowercase = false;
		boolean hasUppercase = false;
		boolean hasDigit = false;
		boolean hasSymbol = false;
		boolean different = true;
		for (int i = 0; i < password.length(); i++) {
			char ch = password.charAt(i);
			if (Character.isLetter(ch)) {
				if (Character.isUpperCase(ch)) {
					hasUppercase = true;
				} else {
					hasLowercase = true;
				}
			} else if (Character.isDigit(ch)) {
				hasDigit = true;
			} else {
				hasSymbol = true;
			}
			for (int j = 0; j < i; j++) {
				if (password.charAt(j) == ch) {
					different = false;
				}
			}
		}
		int answer = 0;
		if (hasLowercase || hasUppercase) {
			answer++;
		}
		if (hasLowercase && hasUppercase) {
			answer++;
		}
		if (hasDigit) {
			answer++;
		}
		if (hasSymbol) {
			answer++;
		}
		if (different) {
			answer++;
		}
		if (password.length() >= 6) {
			answer++;
		}
		if (password.length() > 10) {
			answer++;
		}
		out.printLine("Case #" + testNumber + ":");
		if (answer < 4) {
			out.printLine("weak");
		} else if (answer < 6) {
			out.printLine("normal");
		} else {
			out.printLine("strong");
		}
    }
}
