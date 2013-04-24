package on2013_04.on2013_04_07_OpenCup_America_GP.TaskJ;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskJ {
	static long[] coef = new long[5000];

	static {
		Random random = new Random();
		for (int i = 0; i < 5000; i++)
			coef[i] = random.nextLong();
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char open = in.readCharacter();
		if (open == '0')
			throw new UnknownError();
		Node node = new Node().read(in, 0);
		node.solve();
		Collections.sort(node.answer);
		for (Node list : node.answer)
			out.printLine(list.list);
    }

	static class Node implements Comparable<Node> {
		final List<Node> children = new ArrayList<Node>();
		final List<Node> answer = new ArrayList<Node>();
		int[] list;
		long hash;
		int level;

		public Node read(InputReader in, int level) {
			this.level = level;
			list = new int[level + 1];
			while (in.readCharacter() == '(')
				children.add(new Node().read(in, level + 1));
			return this;
		}

		public void solve() {
			//Counter<Long> counter = new Counter<Long>();
			for (Node node : children) {
				node.solve();
				for (Node result : node.answer) {
					//result.add(level, counter.add(result.hash));
					answer.add(result);
				}
			}
			Collections.sort(answer, new Comparator<Node>() {
				public int compare(Node o1, Node o2) {
					return IntegerUtils.longCompare(o1.hash, o2.hash);
				}
			});
			int acc = 0;
			long lastHash = -1;
			for (Node anAnswer : answer) {
				long curHash = anAnswer.hash;
				if (curHash != lastHash) {
					acc = 0;
					lastHash = curHash;
				}
				++acc;
				anAnswer.add(level, acc);
			}
			answer.add(this);
		}

		private void add(int level, long toAdd) {
			list[level] += toAdd;
			hash += toAdd * coef[level];
		}

		public int compareTo(Node o) {
			for (int i = 0; i < list.length; i++) {
				if (o.list.length == i)
					return 1;
				if (list[i] != o.list[i])
					return list[i] - o.list[i];
			}
			if (o.list.length > list.length)
				return -1;
			return 0;
		}
	}
}
