package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskD {
	int[] globalAnswer;
	AtomicInteger notSolved;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int testCount = in.readInt();
		globalAnswer = new int[testCount];
		int parts = 10;
		int was = 0;
		for (int j = 0; j < parts; j++) {
			int testsInPart = testCount / parts + (j < testCount % parts ? 1 : 0);
			notSolved = new AtomicInteger(testsInPart);
			for (int i = was; i < was + testsInPart; i++) {
				int count = in.readInt();
				int[] profit = IOUtils.readIntArray(in, count);
				int[] parent = IOUtils.readIntArray(in, count - 1);
				MiscUtils.decreaseByOne(parent);
				new Thread(new Solver(i, count, profit, parent)).start();
			}
			was += testsInPart;
			while (notSolved.get() != 0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		for (int i = 0; i < testCount; i++) {
			out.printLine("Case #" + (i + 1) + ":", globalAnswer[i]);
		}
    }

	class Solver implements Runnable {
		int[][] answer;
		int[] profit;
		int[] maxProfit;
		boolean[][] connected;
		private Graph graph;
		int[][] badProfit;
		int count;
		int testNumber;

		Solver(int testNumber, int count, int[] profit, int[] parent) {
			this.testNumber = testNumber;
			this.profit = profit;
			graph = BidirectionalGraph.createGraph(count, ArrayUtils.createOrder(count - 1), parent);
			this.count = count;
		}

		public void run() {
			answer = new int[3 * count - 2][3 * count - 2];
			maxProfit = new int[2 * count - 2];
			badProfit = new int[3 * count - 2][2 * count - 2];
			ArrayUtils.fill(answer, Integer.MIN_VALUE);
			ArrayUtils.fill(badProfit, -1);
			Arrays.fill(maxProfit, -1);
			connected = new boolean[2 * count - 2][count];
			for (int i = 0; i < 2 * count - 2; i++)
				dfs(graph.destination(i), graph.source(i), connected[i]);
			int answer = Integer.MIN_VALUE;
			for (int i = 0; i < count; i++) {
				int result = Integer.MAX_VALUE;
				for (int j = 0; j < count; j++)
					result = Math.min(result, go(i + 2 * count - 2, j + 2 * count - 2));
				answer = Math.max(answer, result);
			}
			globalAnswer[testNumber] = answer;
			notSolved.decrementAndGet();
			System.err.println(testNumber);
		}

		private int go(int firstEdge, int secondEdge) {
			if (answer[firstEdge][secondEdge] != Integer.MIN_VALUE)
				return answer[firstEdge][secondEdge];
			int firstAt = firstEdge < maxProfit.length ? graph.destination(firstEdge) : firstEdge - maxProfit.length;
			int secondAt = secondEdge < maxProfit.length ? graph.destination(secondEdge) : secondEdge - maxProfit.length;
			for (int i = graph.firstOutbound(firstAt); i != -1; i = graph.nextOutbound(i)) {
				if ((i ^ 1) == firstEdge || (i ^ 1) == secondEdge)
					continue;
				if (!connected[i][secondAt]) {
					int delta;
					if (firstEdge < maxProfit.length)
						delta = getProfit(secondEdge, firstAt, i, firstEdge ^ 1);
					else
						delta = getProfit(secondEdge, firstAt, i);
					int current = profit[firstAt] + getMaxProfit(i) - delta;
					answer[firstEdge][secondEdge] = Math.max(answer[firstEdge][secondEdge], current);
				} else
					answer[firstEdge][secondEdge] = Math.max(answer[firstEdge][secondEdge], profit[firstAt] - go(secondEdge, i));
			}
			if (answer[firstEdge][secondEdge] == Integer.MIN_VALUE)
				answer[firstEdge][secondEdge] = profit[firstAt];
			return answer[firstEdge][secondEdge];
		}

		private int getProfit(int startEdge, int badCity, int firstBad, int secondBad) {
			if (!connected[startEdge][badCity])
				return getMaxProfit(startEdge);
			int result = 0;
			for (int i = graph.firstOutbound(graph.destination(startEdge)); i != -1; i = graph.nextOutbound(i)) {
				if ((i ^ 1) != startEdge && i != firstBad && i != secondBad)
					result = Math.max(result, getProfit(i, badCity, firstBad, secondBad));
			}
			if (graph.destination(startEdge) != badCity)
				result += profit[graph.destination(startEdge)];
			return result;
		}

		private int getProfit(int startEdge, int badCity, int firstBad) {
			if (badProfit[startEdge][firstBad] != -1)
				return badProfit[startEdge][firstBad];
			if (startEdge < maxProfit.length && !connected[startEdge][badCity])
				return getMaxProfit(startEdge);
			int result = 0;
			int startCity = startEdge < maxProfit.length ? graph.destination(startEdge) : startEdge - maxProfit.length;
			for (int i = graph.firstOutbound(startCity); i != -1; i = graph.nextOutbound(i)) {
				if ((i ^ 1) != startEdge && i != firstBad)
					result = Math.max(result, getProfit(i, badCity, firstBad));
			}
			if (startCity != badCity)
				result += profit[startCity];
			return badProfit[startEdge][firstBad] = result;
		}

		private int getMaxProfit(int edge) {
			if (maxProfit[edge] != -1)
				return maxProfit[edge];
			maxProfit[edge] = 0;
			for (int i = graph.firstOutbound(graph.destination(edge)); i != -1; i = graph.nextOutbound(i)) {
				if ((i ^ 1) != edge)
					maxProfit[edge] = Math.max(maxProfit[edge], getMaxProfit(i));
			}
			return maxProfit[edge] += profit[graph.destination(edge)];
		}

		private void dfs(int current, int last, boolean[] visited) {
			visited[current] = true;
			for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
				int next = graph.destination(i);
				if (next != last)
					dfs(next, current, visited);
			}
		}
	}
}
