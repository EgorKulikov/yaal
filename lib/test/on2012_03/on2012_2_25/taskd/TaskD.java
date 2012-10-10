package on2012_03.on2012_2_25.taskd;



import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] cost = new int[count];
		int[] size = new int[count];
		IOUtils.readIntArrays(in, cost, size);
		int buyerCount = in.readInt();
		int[] money = new int[buyerCount];
		int[] required = new int[buyerCount];
		IOUtils.readIntArrays(in, money, required);
		for (int i = 0; i < buyerCount; i++)
			required[i]++;
		Map<Integer, State> states = new HashMap<Integer, State>();
		for (int i = 0; i < count; i++)
			states.put(size[i], new State(size[i], cost[i], i + 1));
		for (int i = 0; i < buyerCount; i++) {
			if (states.containsKey(required[i])) {
				State state = states.get(required[i]);
				if (money[i] > state.best) {
					state.secondBest = state.best;
					state.secondBestIndex = state.bestIndex;
					state.best = money[i];
					state.bestIndex = i + 1;
				} else if (money[i] > state.secondBest) {
					state.secondBest = money[i];
					state.secondBestIndex = i + 1;
				}
			} else if (states.containsKey(required[i] - 1) && states.get(required[i] - 1).cost != Integer.MAX_VALUE) {
				State state = new State(required[i], Integer.MAX_VALUE, -1);
				state.best = money[i];
				state.bestIndex = i + 1;
				states.put(required[i], state);
			}
		}
		State[] array = states.values().toArray(new State[states.values().size()]);
		Arrays.sort(array, new Comparator<State>() {
			public int compare(State o1, State o2) {
				return o1.size - o2.size;
			}
		});
		long taken = 0;
		long notTaken = 0;
		int[] lastTaken = new int[array.length];
		boolean[] lastNotTaken = new boolean[array.length];
		for (int i = 0; i < array.length; i++) {
			if (i == 0 || array[i - 1].size != array[i].size - 1) {
				notTaken = taken;
				lastNotTaken[i] = true;
				lastTaken[i] = 0;
				if (array[i].best >= array[i].cost) {
					taken += array[i].cost;
					lastTaken[i] = 1;
				}
			} else {
				long nextNotTaken = taken;
				lastNotTaken[i] = true;
				if (array[i].best >= array[i - 1].cost && nextNotTaken < notTaken + array[i - 1].cost) {
					lastNotTaken[i] = false;
					nextNotTaken = notTaken + array[i - 1].cost;
				}
				long nextTaken = taken;
				lastTaken[i] = 0;
				if (array[i].best >= array[i].cost) {
					nextTaken = taken + array[i].cost;
					lastTaken[i] = 1;
				}
				if (nextNotTaken > nextTaken) {
					nextTaken = nextNotTaken;
					lastTaken[i] = 2;
				}
				if (array[i].best >= Math.max(array[i].cost, array[i - 1].cost) && array[i].secondBest >= Math.min(array[i].cost, array[i - 1].cost) &&
					nextTaken < notTaken + array[i].cost + array[i - 1].cost)
				{
					nextTaken = notTaken + array[i].cost + array[i - 1].cost;
					lastTaken[i] = 3;
				}
				notTaken = nextNotTaken;
				taken = nextTaken;
			}
		}
		out.printLine(taken);
		boolean curTaken = true;
		List<Pair<Integer, Integer>> answer = new ArrayList<Pair<Integer, Integer>>();
		for (int i = array.length - 1; i >= 0; i--) {
			if (curTaken) {
				if (lastTaken[i] == 0)
					continue;
				if (lastTaken[i] == 1) {
					answer.add(Pair.makePair(array[i].bestIndex, array[i].shoeIndex));
					continue;
				}
				if (lastTaken[i] == 3) {
					if (array[i].secondBest >= array[i].cost) {
						answer.add(Pair.makePair(array[i].secondBestIndex, array[i].shoeIndex));
						answer.add(Pair.makePair(array[i].bestIndex, array[i - 1].shoeIndex));
					} else {
						answer.add(Pair.makePair(array[i].secondBestIndex, array[i - 1].shoeIndex));
						answer.add(Pair.makePair(array[i].bestIndex, array[i].shoeIndex));
					}
					curTaken = false;
					continue;
				}
			}
			if (!lastNotTaken[i])
				answer.add(Pair.makePair(array[i].bestIndex, array[i - 1].shoeIndex));
			curTaken = lastNotTaken[i];
		}
		out.printLine(answer.size());
		for (Pair<Integer, Integer> pair : answer) {
			out.printLine(pair.first, pair.second);
		}
	}

	static class State {
		int size;
		int cost;
		int shoeIndex;
		int best;
		int bestIndex;
		int secondBest;
		int secondBestIndex;

		State(int size, int cost, int shoeIndex) {
			this.size = size;
			this.cost = cost;
			this.shoeIndex = shoeIndex;
		}
	}
}
