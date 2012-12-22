package net.egork;

import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int shirazCount = in.readInt();
		shirazCount = Math.min(count, shirazCount);
		Team[] teams = new Team[count];
		for (int i = 0; i < count; i++) {
			int firstSolved = in.readInt();
			int secondSolved = in.readInt();
			teams[i] = new Team(firstSolved, secondSolved, i < shirazCount);
		}
		if (count == 1) {
			out.printLine("0.0000");
			return;
		}
		long delta = shirazCount * (shirazCount - 1);
		List<Rational> more = new ArrayList<Rational>();
		List<Rational> less = new ArrayList<Rational>();
		for (int i = 0; i < shirazCount; i++) {
			for (int j = shirazCount; j < count; j++) {
				if (teams[i].firstSolved == teams[j].firstSolved && teams[i].secondSolved == teams[j].secondSolved) {
					delta++;
					continue;
				}
				if (teams[i].firstSolved >= teams[j].firstSolved && teams[i].secondSolved >= teams[j].secondSolved) {
					delta += 2;
					continue;
				}
				if (teams[i].firstSolved <= teams[j].firstSolved && teams[i].secondSolved <= teams[j].secondSolved)
					continue;
				if (teams[i].firstSolved > teams[j].firstSolved) {
					more.add(new Rational(teams[j].secondSolved - teams[i].secondSolved,
						teams[i].firstSolved - teams[j].firstSolved));
				} else {
					less.add(new Rational(teams[i].secondSolved - teams[j].secondSolved,
						teams[j].firstSolved - teams[i].firstSolved));
				}
			}
		}
		Collections.sort(more);
		Collections.sort(less);
		long answer = 2 * less.size();
		long current = 2 * less.size();
		int index = 0;
		for (Rational value : more) {
			while (index < less.size() && less.get(index).compareTo(value) <= 0) {
				index++;
				current -= 2;
			}
			current += 2;
			answer = Math.max(answer, current);
		}
		answer += delta;
		out.printFormat("%.4f\n", (double)answer / (count - 1) / 2);
	}
}

class Team {
	final int firstSolved;
	final int secondSolved;
	final boolean shiraz;

	Team(int firstSolved, int secondSolved, boolean isShiraz) {
		this.firstSolved = firstSolved;
		this.secondSolved = secondSolved;
		shiraz = isShiraz;
	}
}
