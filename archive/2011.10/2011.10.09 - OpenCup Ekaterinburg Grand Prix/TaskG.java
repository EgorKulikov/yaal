import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TaskG implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		map.put("gold", new HashSet<String>());
		map.put("silver", new HashSet<String>());
		map.put("bronze", new HashSet<String>());
		for (int i = 0; i < 4; i++)
			map.get("gold").add(in.readString());
		for (int i = 0; i < 4; i++)
			map.get("silver").add(in.readString());
		for (int i = 0; i < 4; i++)
			map.get("bronze").add(in.readString());
		int count = in.readInt();
		int maxScore = -1;
		int result = 0;
		for (int i = 0; i < count; i++) {
			int guessCount = in.readInt();
			int score = 0;
			for (int j = 0; j < guessCount; j++) {
				String team = in.readString();
				in.readCharacter();
				String medal = in.readString();
				if (map.get(medal).contains(team))
					score++;
			}
			if (score == maxScore)
				result++;
			else if (score > maxScore) {
				maxScore = score;
				result = 1;
			}
		}
		out.println(result * 5);
	}
}

