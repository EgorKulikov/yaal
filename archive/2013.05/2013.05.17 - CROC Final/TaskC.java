package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] qtyBits = new int[30];
		int[] qtyRequests = new int[30];
		int count = in.readInt();
		int requestCount = in.readInt();
		for (int i = 0; i < count; i++) {
			int cell = in.readInt();
			for (int j = 0; j < 30; j++) {
				if ((cell >> j & 1) == 1)
					qtyBits[j]++;
			}
		}
		for (int i = 0; i < requestCount; i++)
			qtyRequests[in.readInt()]++;
		int answer = 0;
		IntList weights = new IntArrayList();
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < qtyRequests[i]; j++)
				weights.add(1);
			for (int j = 0; j < weights.size() && j < qtyBits[i]; j++)
				answer += weights.get(j);
			IntList newWeights = new IntArrayList();
			for (int j = qtyBits[i]; j < weights.size(); j += 2) {
				int totalWeight = weights.get(j);
				if (j + 1 < weights.size())
					totalWeight += weights.get(j + 1);
				newWeights.add(totalWeight);
			}
			weights = newWeights;
		}
		out.printLine(answer);
    }
}
