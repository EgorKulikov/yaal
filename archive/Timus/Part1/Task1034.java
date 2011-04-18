package Timus.Part1;

import net.egork.collections.Pair;
import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Task1034 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int boardSize = in.readInt();
		Pair<Long, Long>[] queens = Pair.readArray(boardSize, in);
		int result = 0;
		for (int i = 0; i < boardSize; i++) {
			for (int j = i + 1; j < boardSize; j++) {
				for (int k = i + 1; k < boardSize; k++) {
					if (j == k)
						continue;
					Pair<Long, Long> firstQueen = new Pair<Long, Long>(queens[i].first(), queens[j].second());
					Pair<Long, Long> secondQueen = new Pair<Long, Long>(queens[j].first(), queens[k].second());
					Pair<Long, Long> thirdQueen = new Pair<Long, Long>(queens[k].first(), queens[i].second());
					if (attack(firstQueen, secondQueen) || attack(secondQueen, thirdQueen) || attack(thirdQueen,
						firstQueen))
					{
						continue;
					}
					boolean good = true;
					for (int l = 0; good && l < boardSize; l++) {
						good = l == i || l == j || l == k || !attack(firstQueen, queens[l]) &&
							!attack(secondQueen, queens[l]) && !attack(thirdQueen, queens[l]);
					}
					if (good)
						result++;
				}
			}
		}
		out.println(result);
	}

	private boolean attack(Pair<Long, Long> firstQueen, Pair<Long, Long> secondQueen) {
		return firstQueen.first().equals(secondQueen.first()) || firstQueen.second().equals(secondQueen.second()) ||
			firstQueen.first() + firstQueen.second() == secondQueen.first() + secondQueen.second() ||
			firstQueen.first() - firstQueen.second() == secondQueen.first() - secondQueen.second();
	}
}

