import net.egork.collections.Pair;
import net.egork.misc.MiscUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.*;

public class TaskC implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		in.readInt();
		int toReplace = in.readInt();
		char[] number = in.readString().toCharArray();
		Pair<Integer, String> result = Pair.makePair(Integer.MAX_VALUE, "");
		for (char digit = '0'; digit <= '9'; digit++)
			result = MiscUtils.min(result, processDigit(number, digit, toReplace));
		out.println(result.first);
		out.println(result.second);
	}

	private Pair<Integer, String> processDigit(char[] number, char digit, int toReplace) {
		number = number.clone();
		Set<Replacement> replacements = new TreeSet<Replacement>(new Comparator<Replacement>() {
			public int compare(Replacement o1, Replacement o2) {
				int value = Math.abs(o1.from - o1.to) - Math.abs(o2.from - o2.to);
				if (value != 0)
					return value;
				if (o1.from != o2.from)
					return o2.from - o1.from;
				if (o1.from > o1.to)
					return o1.position - o2.position;
				else
					return o2.position - o1.position;
			}
		});
		for (int i = 0; i < number.length; i++) {
			char c = number[i];
			if (c == digit)
				toReplace--;
			else
				replacements.add(new Replacement(number[i], digit, i));
		}
		int index = 0;
		int score = 0;
		for (Replacement replacement : replacements) {
			if (index++ >= toReplace)
				break;
			score += replacement.replace(number);
		}
		return Pair.makePair(score, new String(number));
	}
}

class Replacement {
	public final char from;
	public final char to;
	public final int position;

	Replacement(char from, char to, int position) {
		this.from = from;
		this.to = to;
		this.position = position;
	}

	public int replace(char[] number) {
		number[position] = to;
		return Math.abs(to - from);
	}
}
