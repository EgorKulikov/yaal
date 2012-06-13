package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class ExpectedMaximumMatching {
	Map<Integer, Integer> states = new HashMap<Integer, Integer>();
	int[][] graph = new int[47841][32];
	int curState = 1;
	int[] size = new int[47841];
	int[][] contains = new int[32][];
	int[] bitCount = new int[32];
	Map<Integer, Integer> first = new HashMap<Integer, Integer>();
	int[] goodStates = new int[47841];
	int goodStateCount = 1;

	{
		long time = System.currentTimeMillis();
		for (int i = 0; i < 32; i++) {
			bitCount[i] = Integer.bitCount(i);
			contains[i] = new int[1 << (5 - Integer.bitCount(i))];
			int index = 0;
			for (int j = 0; j < 32; j++) {
				if ((j & i) == i)
					contains[i][index++] = j;
			}
		}
		size[0] = 5;
		generateStates(0, new int[32]);
		goodStates = Arrays.copyOf(goodStates, goodStateCount);
//		System.err.println(states.size());
//		System.err.println(System.currentTimeMillis() - time);
	}

	private int generateStates(int state, int[] counts) {
		state = sort(state);
		if (states.containsKey(state))
			return states.get(state);
		int curState = this.curState;
		states.put(state, curState);
		this.curState++;
		int stateSize = state >> 25;
		size[curState] = stateSize;
		int type = 0;
		for (int i = 0; i < 32; i++) {
			boolean good = true;
			for (int j : contains[i]) {
				counts[j]++;
				if (counts[j] > bitCount[j]) {
					good = false;
					for (int k : contains[i]) {
						counts[k]--;
						if (k == j)
							break;
					}
					break;
				}
			}
			if (good) {
				type += 1 << i;
				if (stateSize == 4)
					graph[curState][i] = 0;
				else {
					int newState = state + (i << (5 * stateSize)) + (1 << 25);
					graph[curState][i] = generateStates(newState, counts);
				}
				for (int j : contains[i])
					counts[j]--;
			} else
				graph[curState][i] = curState;
		}
		if (stateSize == 4) {
			if (first.containsKey(type)) {
				states.put(state, first.get(type));
				return first.get(type);
			} else
				first.put(type, curState);
		}
		goodStates[goodStateCount++] = curState;
		return curState;
	}

	private int sort(int state) {
		int size = state >> 25;
		for (int i = size - 1; i > 0; i--) {
			int current = (state >> (5 * i)) & 31;
			int previous = (state >> (5 * (i - 1))) & 31;
			if (previous <= current)
				return state;
			state -= current << (5 * i);
			state -= previous << (5 * (i - 1));
			state += current << (5 * (i - 1));
			state += previous << (5 * i);
		}
		return state;
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		double[][] probability = IOUtils.readDoubleTable(in, rowCount, columnCount);
		double[] current = new double[47841];
		double[] next = new double[47841];
		current[1] = 1;
		int bitSize = 1 << rowCount;
		double[] curProbability = new double[bitSize];
		for (int i = 0; i < columnCount; i++) {
			Arrays.fill(next, 0);
			for (int j = 0; j < bitSize; j++) {
				curProbability[j] = 1;
				for (int k = 0; k < rowCount; k++) {
					if ((j >> k & 1) == 1)
						curProbability[j] *= probability[k][i];
					else
						curProbability[j] *= 1 - probability[k][i];
				}
			}
			for (int k : goodStates) {
				if (current[k] < 1e-12)
					continue;
				for (int j = 0; j < bitSize; j++)
					next[graph[k][j]] += current[k] * curProbability[j];
			}
			double[] temp = current;
			current = next;
			next = temp;
		}
		double answer = 0;
		for (int i : goodStates)
			answer += size[i] * current[i];
		out.printLine(answer);
	}
}
