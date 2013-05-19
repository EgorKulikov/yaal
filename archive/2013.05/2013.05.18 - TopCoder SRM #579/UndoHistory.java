package net.egork;

import net.egork.collections.set.EHashSet;

import java.util.Set;

public class UndoHistory {
    public int minPresses(String[] lines) {
		Set<String> undo = new EHashSet<String>();
		undo.add("");
		int answer = 0;
		String last = "";
		for (String line : lines) {
			int current = Integer.MAX_VALUE;
			if (line.startsWith(last))
				current = line.length() - last.length() + 1;
			for (String s : undo) {
				if (line.startsWith(s))
					current = Math.min(current, line.length() - s.length() + 3);
			}
			answer += current;
			for (int i = 1; i <= line.length(); i++)
				undo.add(line.substring(0, i));
			last = line;
		}
		return answer;
    }
}
