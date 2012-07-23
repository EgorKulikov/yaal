import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int wordCount = in.readInt();
		String[] badWords = IOUtils.readStringArray(in, wordCount);
		String name = in.readString();
		char lucky = in.readCharacter();
		boolean[] mustReplace = new boolean[name.length()];
		for (int i = 0; i < name.length(); i++) {
			for (String word : badWords) {
				if (i + word.length() <= name.length() && name.substring(i, i + word.length()).equalsIgnoreCase(word))
					Arrays.fill(mustReplace, i, i + word.length(), true);
			}
		}
		char notLucky = Character.toLowerCase(lucky) == 'a' ? 'b' : 'a';
		StringBuilder answer = new StringBuilder(name.length());
		for (int i = 0; i < name.length(); i++) {
			if (mustReplace[i]) {
				if (Character.isUpperCase(name.charAt(i))) {
					if (Character.toUpperCase(lucky) == name.charAt(i))
						answer.append(Character.toUpperCase(notLucky));
					else
						answer.append(Character.toUpperCase(lucky));
				} else {
					if (Character.toLowerCase(lucky) == name.charAt(i))
						answer.append(Character.toLowerCase(notLucky));
					else
						answer.append(Character.toLowerCase(lucky));
				}
			} else
				answer.append(name.charAt(i));
		}
		out.println(answer);
	}
}

