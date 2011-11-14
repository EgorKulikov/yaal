import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskG implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int length = in.readInt();
		int[] skill = new int[2 * count];
		for (int i = 0; i < count; i++) {
			int firstExplanation = in.readInt();
			int firstUnderstanding = in.readInt();
			int secondExplanation = in.readInt();
			int secondUnderstanding = in.readInt();
			skill[i] = firstExplanation + secondUnderstanding;
			skill[i + count] = firstUnderstanding + secondExplanation;
		}
		int cardCount = in.readInt();
		String[] word = new String[cardCount];
		int[] hardness = new int[cardCount];
		for (int i = 0; i < cardCount; i++) {
			word[i] = in.readString();
			hardness[i] = in.readInt();
		}
		@SuppressWarnings({"unchecked"})
		List<String>[] points = new List[count];
		for (int i = 0; i < count; i++)
			points[i] = new ArrayList<String>();
		boolean[] explained = new boolean[cardCount];
		int[][] timeSpent = new int[count][cardCount];
		int cardIndex = 0;
		int notExplained = cardCount;
		for (int i = 0; notExplained != 0; i++) {
			int teamIndex = i % count;
			int playerIndex = i % (2 * count);
			int time = length;
			while (time != 0 && notExplained != 0) {
				while (explained[cardIndex]) {
					if (++cardIndex == cardCount)
						cardIndex = 0;
				}
				int timeRequired = Math.max(1, hardness[cardIndex] - skill[playerIndex] -
					timeSpent[teamIndex][cardIndex]);
				if (timeRequired > time) {
					timeSpent[teamIndex][cardIndex] += time;
					time = 0;
				} else {
					points[teamIndex].add(word[cardIndex]);
					explained[cardIndex] = true;
					time -= timeRequired;
					notExplained--;
				}
				cardIndex++;
				if (cardIndex == cardCount)
					cardIndex = 0;
			}
		}
		for (List<String> team : points) {
			out.print(team.size());
			for (String s : team)
				out.print(" " + s);
			out.println();
		}
	}
}

