package Timus.Part1;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class Task1089 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		Set<String> correct = new HashSet<String>();
		Map<String, String> errors = new HashMap<String, String>();
		while (true) {
			String word = in.readString();
			if ("#".equals(word))
				break;
			char[] wordAsArray = word.toCharArray();
			correct.add(word);
			for (int i = 0; i < wordAsArray.length; i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					wordAsArray[i] = c;
					errors.put(new String(wordAsArray), word);
				}
				wordAsArray[i] = word.charAt(i);
			}
		}
		StringBuilder word = new StringBuilder();
		int errorCount = 0;
		while (true) {
			int ch = in.read();
			if (ch == -1)
				break;
			if (Character.isLetter(ch))
				word.append((char) ch);
			else {
				if (word.length() != 0) {
					if (correct.contains(word.toString()))
						out.print(word);
					else if (errors.containsKey(word.toString())) {
						out.print(errors.get(word.toString()));
						errorCount++;
					} else
						out.print(word);
					word = new StringBuilder();
				}
				out.print((char) ch);
			}
		}
		out.println(errorCount);
	}
}

