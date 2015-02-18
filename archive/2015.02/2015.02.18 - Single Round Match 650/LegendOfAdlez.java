package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;

import java.util.Arrays;

public class LegendOfAdlez {
	long[][] answer;
	long[][] directAnswer;
	long[][] subAnswer;
	long[] anyAnswer;
	Graph graph;
	int[] size;
	int end;
	long[][] c = IntegerUtils.generateBinomialCoefficients(60);

    public long safePatterns(int[] door1, int[] door2, int[] lock) {
		int count = door1.length + 1;
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (int i = 0; i < count - 1; i++) {
			if (lock[i] == 0) {
				setSystem.join(door1[i], door2[i]);
			}
		}
		answer = new long[count][count];
		ArrayUtils.fill(answer, -1);
		size = new int[count];
		graph = new BidirectionalGraph(count);
		for (int i = 0; i < count - 1; i++) {
			if (lock[i] == 1) {
				graph.addSimpleEdge(setSystem.get(door1[i]), setSystem.get(door2[i]));
			}
		}
		for (int i = 0; i < count; i++) {
			size[setSystem.get(i)]++;
			answer[setSystem.get(count - 1)][i] = 0;
		}
		subAnswer = new long[2 * count][count];
		ArrayUtils.fill(subAnswer, -1);
		directAnswer = new long[count][count];
		ArrayUtils.fill(directAnswer, -1);
		anyAnswer = new long[count];
		Arrays.fill(anyAnswer, -1);
		end = setSystem.get(count - 1);
		size[end]--;
		return (1L << (count - 1)) - go(setSystem.get(0), 0, -1);
    }

	private long go(int vertex, int keys, int last) {
		if (answer[vertex][keys] != -1) {
			return answer[vertex][keys];
		}
		answer[vertex][keys] = 0;
		for (int i = 0; i <= size[vertex]; i++) {
			answer[vertex][keys] += goDirect(vertex, keys + i, last) * c[size[vertex]][i];
		}
		return answer[vertex][keys];
	}

	private long goDirect(int vertex, int keys, int last) {
		if (directAnswer[vertex][keys] != -1) {
			return directAnswer[vertex][keys];
		}
		return directAnswer[vertex][keys] = goSub(graph.firstOutbound(vertex), keys, last);
	}

	private long goSub(int edge, int keys, int last) {
		if (edge == -1) {
			if (keys == 0) {
				return 1;
			}
			return 0;
		}
		if (subAnswer[edge][keys] != -1) {
			return subAnswer[edge][keys];
		}
		int from = graph.source(edge);
		int to = graph.destination(edge);
		if (to == last) {
			return subAnswer[edge][keys] = goSub(graph.nextOutbound(edge), keys, last);
		}
		subAnswer[edge][keys] = (goAny(to, from) - (keys == 0 ? 0 : go(to, 0, from))) * goSub(graph.nextOutbound(edge), keys, last);
		for (int i = 1; i <= keys; i++) {
			subAnswer[edge][keys] += (go(to, i - 1, from) - (i == keys ? 0 : go(to, i, from))) * goSub(graph.nextOutbound(edge), keys - i, last);
		}
		return subAnswer[edge][keys];
	}

	private long goAny(int vertex, int last) {
		if (anyAnswer[vertex] != -1) {
			return anyAnswer[vertex];
		}
		anyAnswer[vertex] = 1L << size[vertex];
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			if (graph.destination(i) != last) {
				anyAnswer[vertex] *= goAny(graph.destination(i), vertex);
			}
		}
		return anyAnswer[vertex];
	}
}
