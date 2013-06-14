import net.egork.utils.Exit;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class VoteForTheNoodleSoup implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		if (count == 0) {
			Exit.exit(in, out);
			return;
		}
		int result = 0;
		for (int i = 0; i < count; i++) {
			char vote = in.readCharacter();
			int score = in.readInt();
			if (vote == 'P')
				score--;
			else
				score++;
			result = Math.max(result, Math.abs(score));
			if (result % 2 != Math.abs(score) % 2)
				result++;
		}
		out.println(result);
	}
}

