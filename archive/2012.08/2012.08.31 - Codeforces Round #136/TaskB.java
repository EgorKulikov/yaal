package net.egork;

import net.egork.collections.map.Counter;
import net.egork.collections.map.Indexer;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Map;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		Counter<Integer> counter = new Counter<Integer>();
		for (int i : array)
			counter.add(i);
		int[] good = new int[1000];
		int size = 0;
		Indexer<Integer> indexer = new Indexer<Integer>();
		for (Map.Entry<Integer, Long> entry : counter.entrySet()) {
			if (entry.getKey() <= entry.getValue()) {
				good[size++] = entry.getKey();
				indexer.get(entry.getKey());
			}
		}
		int[][] positions = new int[size][];
		for (int i = 0; i < size; i++)
			positions[i] = new int[(int)(long)counter.get(good[i])];
		int[] index = new int[size];
		for (int i = 0; i < count; i++) {
			if (counter.get(array[i]) >= array[i]) {
				int j = indexer.get(array[i]);
				positions[j][index[j]++] = i;
			}
		}
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int answer = 0;
			for (int j = 0; j < size; j++) {
				int fromIndex = Arrays.binarySearch(positions[j], from);
				if (fromIndex < 0)
					fromIndex = -fromIndex - 1;
				int toIndex = Arrays.binarySearch(positions[j], to);
				if (toIndex < 0)
					toIndex = -toIndex - 2;
				if (toIndex - fromIndex + 1 == good[j])
					answer++;
			}
			out.printLine(answer);
		}
	}
}
