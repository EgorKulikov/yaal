package April2011.UVaHugeEasyContestII;

import net.egork.collections.CollectionUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.*;

public class TaskI implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		Set<List<Integer>> dice = new HashSet<List<Integer>>();
		for (int i = 0; i < 2; i++) {
			int left = in.readCharacter() - '0';
			int right = in.readCharacter() - '0';
			int top = in.readCharacter() - '0';
			int face = in.readCharacter() - '0';
			int bottom = in.readCharacter() - '0';
			int back = in.readCharacter() - '0';
			List<Integer> die = new ArrayList<Integer>(3);
			boolean type = false;
			List<Integer> end = new ArrayList<Integer>(3);
			die.add(Math.min(left, right));
			end.add(Math.max(left, right));
			type ^= Math.min(left, right) == left;
			die.add(Math.min(top, bottom));
			end.add(Math.max(top, bottom));
			type ^= Math.min(top, bottom) == top;
			die.add(Math.min(face, back));
			end.add(Math.max(face, back));
			type ^= Math.min(face, back) == face;
			if (type) {
				Collections.reverse(die);
				Collections.reverse(end);
			}
			int min = CollectionUtils.minElement(die);
			while (die.get(0) != min) {
				CollectionUtils.rotate(die);
				CollectionUtils.rotate(end);
			}
			die.addAll(end);
			dice.add(die);
		}
		if (dice.size() == 2)
			out.println("Not Equal");
		else
			out.println("Equal");
	}
}

