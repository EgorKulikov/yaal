package April2011.CodeforcesBetaRound66;

import net.egork.collections.ArrayUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int racerCount = in.readInt();
		final String[] name = new String[racerCount];
		final int[] points = new int[racerCount];
		for (int i = 0; i < racerCount; i++) {
			name[i] = in.readString();
			points[i] = in.readInt();
		}
		int prizePositions = in.readInt();
		int[] prizes = new int[racerCount];
		for (int i = 0; i < prizePositions; i++)
			prizes[i] = in.readInt();
		String pilotName = in.readString();
		Arrays.sort(prizes);
		Integer[] table = ArrayUtils.order(racerCount, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (points[o1] != points[o2])
					return points[o2] - points[o1];
				return name[o1].compareTo(name[o2]);
			}
		});
		int currentPlace = 1;
		int index = -1;
		for (int i : table) {
			if (name[i].equals(pilotName)) {
				index = i;
				break;
			}
			currentPlace++;
		}
		int maxPoints = points[index] + prizes[racerCount - 1];
		int prizeIndex = 0;
		int result = currentPlace;
		for (int i : table) {
			if (i == index)
				break;
			if (prizes[prizeIndex] + points[i] < maxPoints || prizes[prizeIndex] + points[i] == maxPoints && pilotName.compareTo(name[i]) < 0) {
				prizeIndex++;
				result--;
			}
		}
		int minPlace = result;
		int minPoints = points[index] + prizes[0];
		prizeIndex = racerCount - 1;
		result = currentPlace;
		for (int ii = racerCount - 1; ii >= 0; ii--) {
			int i = table[ii];
			if (i == index)
				break;
			if (prizes[prizeIndex] + points[i] > minPoints || prizes[prizeIndex] + points[i] == minPoints && pilotName.compareTo(name[i]) > 0) {
				prizeIndex--;
				result++;
			}
		}
		out.println(minPlace + " " + result);
	}
}

