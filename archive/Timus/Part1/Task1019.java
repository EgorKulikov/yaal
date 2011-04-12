package Timus.Part1;

import net.egork.collections.Pair;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class Task1019 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		@SuppressWarnings({"unchecked"})
		Pair<Long, Long>[] segments = new Pair[n];
		boolean[] c = new boolean[n];
		for (int i = 0; i < n; i++) {
			segments[i] = Pair.readPair(in);
			c[i] = in.readCharacter() == 'b';
		}
		NavigableSet<Pair<Long, Long>> white = new TreeSet<Pair<Long, Long>>(new Pair.Comparator<Long, Long>());
		white.add(new Pair<Long, Long>(0L, 1000000000L));
		for (int i = 0; i < n; i++) {
			Pair<Long, Long> segment = segments[i];
			Pair<Long, Long> left = new Pair<Long, Long>(segment.first(), segment.first());
			Pair<Long, Long> right = new Pair<Long, Long>(segment.second(), segment.second());
			SortedSet<Pair<Long, Long>> leftSet = white.headSet(left);
			Pair<Long, Long> last = leftSet.last();
			if (last.second() > segment.first()) {
				white.remove(last);
				white.add(new Pair<Long, Long>(last.first(), segment.first()));
				if (last.second() > segment.second())
					white.add(new Pair<Long, Long>(segment.second(), last.second()));
			}
			SortedSet<Pair<Long, Long>> rightSet = white.headSet(right);
			last = rightSet.last();
			if (last.second() > segment.second()) {
				white.remove(last);
				white.add(new Pair<Long, Long>(segment.second(), last.second()));
			}
			List<Pair<Long, Long>> toRemove = new ArrayList<Pair<Long, Long>>(white.tailSet(left).headSet(right));
			white.removeAll(toRemove);
			if (!c[i]) {
				last = white.headSet(left).last();
				if (last.second().equals(segment.first())) {
					white.remove(last);
					segment = new Pair<Long, Long>(last.first(), segment.second());
				}
				last = white.tailSet(right).first();
				if (last.first().equals(segment.second())) {
					white.remove(last);
					segment = new Pair<Long, Long>(segment.first(), last.second());
				}
				white.add(segment);
			}
		}
		long maxLength = 0;
		Pair<Long, Long> answer = null;
		for (Pair<Long, Long> segment : white) {
			if (segment.second() - segment.first() > maxLength) {
				maxLength = segment.second() - segment.first();
				answer = segment;
			}
		}
		if (answer == null)
			throw new RuntimeException();
		out.println(answer.first() + " " + answer.second());
	}
}

