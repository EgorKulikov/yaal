package on2014_08.on2014_08_26_Codeforces_Round__263__Div__1_.B___Appleman_and_Tree;



import net.egork.chelper.task.Test;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.io.StringBufferInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskB {
	static final long MOD = (long) (1e9 + 7);
	long[][] answer;
	Graph graph;
	int count;
	int[] isBlack;
	static int test;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		count = in.readInt();
		graph = new Graph(count);
		for (int i = 1; i < count; i++) {
			graph.addSimpleEdge(in.readInt(), i);
		}
		isBlack = IOUtils.readIntArray(in, count);
		answer = new long[2][count];
		ArrayUtils.fill(answer, -1);
		out.printLine(dfs(1, 0));
		test++;
	}

	private long dfs(int needBlack, int id) {
		if (answer[needBlack][id] != -1) {
			return answer[needBlack][id];
		}
		if (needBlack == 0 && isBlack[id] == 1) {
			return answer[needBlack][id] = 0;
		}
		if (isBlack[id] == 1 || needBlack == 0) {
			answer[needBlack][id] = 1;
			for (int i = graph.firstOutbound(id); i != -1; i = graph.nextOutbound(i)) {
				answer[needBlack][id] *= dfs(0, graph.destination(i)) + dfs(1, graph.destination(i));
				answer[needBlack][id] %= MOD;
			}
		} else {
			IntList black = new IntArrayList();
			IntList total = new IntArrayList();
			for (int i = graph.firstOutbound(id); i != -1; i = graph.nextOutbound(i)) {
				black.add((int) dfs(1, graph.destination(i)));
				total.add((int) ((dfs(0, graph.destination(i)) + dfs(1, graph.destination(i))) % MOD));
			}
			int zeroes = total.count(0);
			if (zeroes >= 2) {
				return answer[needBlack][id] = 0;
			}
			if (zeroes == 1) {
				answer[needBlack][id] = 1;
				for (int i = 0; i < black.size(); i++) {
					if (total.get(i) == 0) {
						answer[needBlack][id] *= black.get(i);
					} else {
						answer[needBlack][id] *= total.get(i);
					}
					answer[needBlack][id] %= MOD;
				}
				return answer[needBlack][id];
			}
			long product = 1;
			for (int i = 0; i < total.size(); i++) {
				product *= total.get(i);
				product %= MOD;
			}
			answer[needBlack][id] = 0;
			for (int i = 0; i < black.size(); i++) {
				answer[needBlack][id] += product * IntegerUtils.reverse(total.get(i), MOD) % MOD * black.get(i) % MOD;
			}
			answer[needBlack][id] %= MOD;
		}
		return answer[needBlack][id];
	}
}
