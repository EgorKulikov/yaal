package on2015_02.on2015_02_02_Codeforces_Round__290__Div__1_.B___Fox_And_Jumping;



import net.egork.collections.intcollection.IntHashMap;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		IntHashMap answer = new IntHashMap();
		List<Card> queue = new ArrayList<>();
		int[] cards = IOUtils.readIntArray(in, count);
		int[] cost = IOUtils.readIntArray(in, count);
		for (int i = 0; i < count; i++) {
			if (!answer.contains(cards[i])) {
				queue.add(new Card(cards[i]));
				answer.put(cards[i], cost[i]);
			} else if (answer.get(cards[i]) > cost[i]) {
				answer.put(cards[i], cost[i]);
			}
		}
		for (int i = 0; i < queue.size(); i++) {
			Card best = null;
			for (Card card : queue) {
				if (!card.used && (best == null || best.key > card.key)) {
					best = card;
				}
			}
			best.used = true;
			for (int j = 0; j < queue.size(); j++) {
				Card card = queue.get(j);
				int gcd = IntegerUtils.gcd(card.key, best.key);
				int totalCost = answer.get(card.key) + answer.get(best.key);
				if (!answer.contains(gcd)) {
					queue.add(new Card(gcd));
					answer.put(gcd, totalCost);
				} else if (answer.get(gcd) > totalCost) {
					answer.put(gcd, totalCost);
				}
			}
		}
		if (!answer.contains(1)) {
			out.printLine(-1);
		} else {
			out.printLine(answer.get(1));
		}
    }

	static class Card {
		int key;
		boolean used;

		public Card(int key) {
			this.key = key;
		}
	}
}
