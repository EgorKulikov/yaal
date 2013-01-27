import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class PopularRiceRecipe implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int voteCount = in.readInt();
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < voteCount; i++) {
			String user = in.readString();
			char vote = in.readCharacter();
			map.put(user, vote == '+' ? 1 : -1);
		}
		int result = 0;
		for (int vote : map.values())
			result += vote;
		out.println(result);
	}
}

