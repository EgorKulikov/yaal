package April2011.CodeforcesBetaRound69;

import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		Map<String, Integer> index = new HashMap<String, Integer>();
		index.put("Anka", 0);
		index.put("Chapay", 1);
		index.put("Cleo", 2);
		index.put("Troll", 3);
		index.put("Dracul", 4);
		index.put("Snowy", 5);
		index.put("Hexadecimal", 6);
		int likeCount = in.readInt();
		boolean[][] likes = new boolean[7][7];
		for (int i = 0; i < likeCount; i++) {
			String first = in.readString();
			in.readString();
			String second = in.readString();
			likes[index.get(first)][index.get(second)] = true;
		}
		int minDifference = Integer.MAX_VALUE;
		int maxLikes = 0;
		int mefistoExp = in.readInt();
		int diabloExp = in.readInt();
		int baalExp = in.readInt();
		for (int i = 1; i < 128; i++) {
			for (int j = i; j != 0; j = (j - 1) & i) {
				@SuppressWarnings({"UnnecessaryLocalVariable"}) 
				int firstSet = j;
				int secondSet = i - j;
				int thirdSet = 127 - i;
				int firstCount = Integer.bitCount(firstSet);
				int secondCount = Integer.bitCount(secondSet);
				int thirdCount = Integer.bitCount(thirdSet);
				if (firstCount == 0 || secondCount == 0 || thirdCount == 0)
					continue;
				int currentDifference = Math.max(Math.abs(mefistoExp / firstCount - diabloExp / secondCount), Math.max(
					Math.abs(mefistoExp / firstCount - baalExp / thirdCount), Math.abs(diabloExp / secondCount - baalExp / thirdCount)));
				if (currentDifference > minDifference)
					continue;
				int currentLikes = 0;
				for (int k = 0; k < 7; k++) {
					for (int l = 0; l < 7; l++) {
						if (k == l)
							continue;
						int mask = (1 << k) + (1 << l);
						if (likes[k][l] && ((firstSet & mask) == mask || (secondSet & mask) == mask || (thirdSet & mask) == mask))
							currentLikes++;
					}
				}
				if (currentDifference < minDifference || currentLikes > maxLikes) {
					minDifference = currentDifference;
					maxLikes = currentLikes;
				}
			}
		}
		out.println(minDifference + " " + maxLikes);
	}
}

