package on2012_05.on2012_4_1.digitsforest;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class DigitsForest {
	private static final int MODULE = 4 * 3 * 5 * 7;
	int[][] lcm = new int[MODULE + 1][8];
	int[][] remainder = new int[MODULE][8];
	int[] moduleToIndex = new int[MODULE + 1];
	int[] indexToModule = new int[24];
	int[][] queues = new int[8][MODULE * 24 * 100];
	int[] queueSize = new int[8];
	int[] answer = new int[MODULE * 24 * 100];
	int curModule;
	int curRemainder;
	int curVertex;

	{
		for (int i = 1; i < lcm.length; i++) {
			for (int j = 1; j <= 7; j++)
				lcm[i][j] = (int) (i * j / IntegerUtils.gcd(i, j));
		}
		for (int i = 0; i < MODULE; i++) {
			for (int j = 1; j <= 7; j++)
				remainder[i][j] = (i * 10 + j) % MODULE;
		}
		List<Long> divisors = IntegerUtils.getDivisors(MODULE);
		for (int i = 0; i < divisors.size(); i++) {
			moduleToIndex[(int)(long)divisors.get(i)] = i;
			indexToModule[i] = (int)(long)divisors.get(i);
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		int[] digit = IOUtils.readIntArray(in, count);
		int[][] graph = IOUtils.readIntTable(in, count, count);
		Arrays.fill(queueSize, 0);
		Arrays.fill(answer, Integer.MAX_VALUE);
		queueSize[digit[0]] = 1;
		int start = encode(digit[0], digit[0], 0);
		queues[digit[0]][0] = start;
		answer[start] = digit[0];
		int emptyCount = 0;
		for (int i = 0; emptyCount < 8; i++) {
			if (queueSize[i & 7] == 0) {
				emptyCount++;
				continue;
			}
			emptyCount = 0;
			for (int j = 0; j < queueSize[i & 7]; j++) {
				int position = queues[i & 7][j];
				if (answer[position] == -1)
					continue;
				decode(position);
				if (curVertex == count - 1 && curRemainder % curModule == 0) {
					out.printLine(i);
					return;
				}
				for (int k = 0; k < count; k++) {
					if (graph[curVertex][k] == 1) {
						int next = encode(lcm[curModule][digit[k]], remainder[curRemainder][digit[k]], k);
						if (answer[next] > answer[position] + digit[k]) {
							answer[next] = answer[position] + digit[k];
							queues[(i + digit[k]) & 7][queueSize[(i + digit[k]) & 7]++] = next;
						}
					}
				}
				answer[position] = -1;
			}
			queueSize[i & 7] = 0;
		}
		out.printLine(-1);
	}

	private int encode(int module, int remainder, int index) {
		return (moduleToIndex[module] * MODULE + remainder) * 100 + index;
	}

	private void decode(int position) {
		curVertex = position % 100;
		position /= 100;
		curRemainder = position % MODULE;
		position /= MODULE;
		curModule = indexToModule[position];
	}
}
