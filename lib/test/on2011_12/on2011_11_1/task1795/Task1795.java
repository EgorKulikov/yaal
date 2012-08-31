package on2011_12.on2011_11_1.task1795;



import net.egork.collections.Pair;
import net.egork.collections.map.CPPMap;
import net.egork.misc.Factory;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Task1795 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int goodCount = in.readInt();
		Map<String, Integer> goods = new CPPMap<String, Integer>(new Factory<Integer>() {
			public Integer create() {
				return 0;
			}
		});
		for (int i = 0; i < goodCount; i++) {
			int quantity = in.readInt();
			in.readString();
			goods.put(in.readString(), quantity);
		}
		Deque<Pair<String, Integer>> deque = new ArrayDeque<Pair<String, Integer>>();
		int lineCount = in.readInt();
		for (int i = 0; i < lineCount; i++) {
			int quantity = in.readInt();
			in.readString();
			String good = in.readString();
			deque.add(Pair.makePair(good, quantity));
		}
		deque.add(Pair.makePair("", 0));
		int answer = 0;
		while (deque.peekFirst().first.length() > 0) {
			String good = deque.peekFirst().first;
			int quantity = deque.pollFirst().second;
			answer++;
			if (goods.get(good) == 0)
				continue;
			if (goods.get(good) >= quantity) {
				goods.put(good, goods.get(good) - quantity);
				continue;
			}
			Pair<String, Integer> next = deque.pollFirst();
			deque.addFirst(Pair.makePair(good, goods.get(good)));
			deque.addFirst(next);
		}
		out.printLine(answer);
	}
}
