package on2012_10.on2012_10_22_ACM_ICPC_NEERC_Southern_Subregional.TaskK;



import net.egork.collections.map.Indexer;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskK {
	int size = (1 << 23) - 239;
	int step = size / 3 - 239;

	long[] first = new long[size];
	long[] second = new long[size];
	int[] value = new int[size];

	void add(long fKey, long sKey) {
		int index = (int) ((fKey ^ sKey) % size);
		while ((first[index] != 0 || second[index] != 0) && (first[index] != fKey || second[index] != sKey)) {
			index += step;
			if (index >= size)
				index -= size;
		}
		first[index] = fKey;
		second[index] = sKey;
		value[index]++;
	}

	int get(long fKey, long sKey) {
		int index = (int) ((fKey ^ sKey) % size);
		while ((first[index] != 0 || second[index] != 0) && (first[index] != fKey || second[index] != sKey)) {
			index += step;
			if (index >= size)
				index -= size;
		}
		return value[index];
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Indexer<String> indexer = new Indexer<String>();
//		Counter<State> counter = new Counter<State>();
		long[] indices = new long[4];
		for (int i = 0; i < count; i++) {
			int size = in.readInt();
			for (int j = 0; j < size; j++)
				indices[j] = indexer.get(in.readString()) + 1;
			Arrays.sort(indices, 0, size);
			for (int j = 0; j < size; j++)
				add(indices[j], 0);
			for (int j = 0; j < size; j++) {
				for (int k = j + 1; k < size; k++)
					add((indices[k] << 32) + indices[j], 0);
			}
			for (int j = 0; j < size; j++) {
				for (int k = j + 1; k < size; k++) {
					for (int l = k + 1; l < size; l++)
						add((indices[k] << 32) + indices[j], indices[l]);
				}
			}
			if (size == 4)
				add((indices[1] << 32) + indices[0], (indices[3] << 32) + indices[2]);
//			for (int j = 1; j < (1 << size); j++) {
//				long first = 0;
//				long second = 0;
//				int total = 0;
//				for (int k = 0; k < size; k++) {
//					if ((j >> k & 1) == 1) {
//						if (total == 0)
//							first += indices[k];
//						else if (total == 1)
//							first += (long)indices[k] << 32;
//						else if (total == 2)
//							second += indices[k];
//						else
//							second += (long)indices[k] << 32;
//						total++;
//					}
//				}
//				counter.add(new State(first, second));
//				add(first, second);
//			}
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int size = in.readInt();
			for (int j = 0; j < size; j++) {
				String key = in.readString();
				if (!indexer.containsKey(key)) {
					for (int k = j + 1; k < size; k++)
						in.readString();
					out.printLine(0);
					size = 0;
					break;
				}
				indices[j] = indexer.get(key) + 1;
			}
			if (size == 0)
				continue;
			Arrays.sort(indices, 0, size);
			long first = 0;
			long second = 0;
			int total = 0;
			for (int k = 0; k < size; k++) {
				if (total == 0)
					first += indices[k];
				else if (total == 1)
					first += indices[k] << 32;
				else if (total == 2)
					second += indices[k];
				else
					second += indices[k] << 32;
				total++;
			}
			out.printLine(get(first, second));
		}
	}
}
