package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.InputMismatchException;

public class CANnHAN {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		try {
			String name = in.readLine();
			if (Character.isUpperCase(name.charAt(0)) || name.charAt(0) == '#' || name.charAt(name.length() - 1) == '#') {
				out.printLine("Error!");
				return;
			}
			boolean hasCapitals = false;
			boolean hasHash = false;
			for (int i = 0; i < name.length(); i++) {
				if (Character.isUpperCase(name.charAt(i))) {
					hasCapitals = true;
				}
				if (name.charAt(i) == '#') {
					hasHash = true;
				}
				if (!Character.isLetter(name.charAt(i)) && name.charAt(i) != '#') {
					out.printLine("Error!");
					return;
				}
			}
			if (hasCapitals && hasHash) {
				out.printLine("Error!");
				return;
			}
			if (hasCapitals) {
				StringBuilder result = new StringBuilder();
				for (int i = 0; i < name.length(); i++) {
					if (Character.isUpperCase(name.charAt(i))) {
						result.append('#');
						result.append(Character.toLowerCase(name.charAt(i)));
					} else {
						result.append(name.charAt(i));
					}
				}
				out.printLine(result);
			} else {
				StringBuilder result = new StringBuilder();
				boolean capitalize = false;
				for (int i = 0; i < name.length(); i++) {
					if (name.charAt(i) == '#') {
						if (capitalize) {
							out.printLine("Error!");
							return;
						}
						capitalize = true;
					} else {
						if (capitalize) {
							result.append(Character.toUpperCase(name.charAt(i)));
							capitalize = false;
						} else {
							result.append(name.charAt(i));
						}
					}
				}
				out.printLine(result);
			}
		} catch (InputMismatchException e) {
			throw new UnknownError();
		}
    }
}
