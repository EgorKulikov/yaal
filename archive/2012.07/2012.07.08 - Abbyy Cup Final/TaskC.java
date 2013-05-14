package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	long[] power;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] treeIndex = new int[count];
		int[] vertex = new int[count];
		char[] label = new char[count];
		for (int i = 0; i < count; i++) {
			treeIndex[i] = in.readInt() - 1;
			vertex[i] = in.readInt() - 1;
			label[i] = in.readCharacter();
		}
		power = new long[count + 1];
		power[0] = 1;
		for (int i = 1; i <= count; i++) {
			power[i] = power[i - 1] * 43;
		}
		int firstCount = 0;
		int secondCount = 0;
		for (int i : treeIndex) {
			if (i == 0)
				firstCount++;
			else
				secondCount++;
		}
		long[] hashFirst = new long[firstCount + 1];
		int[] lengthFirst = new int[firstCount + 1];
		long[] hashSecond = new long[secondCount + 1];
		int[] lengthSecond = new int[secondCount + 1];
		int[] parent = new int[secondCount + 1];
		int maxFirstDepth = 0;
		int firstIndex = 0;
		int secondIndex = 0;
		Hash hash = new Hash();
		for (int i = 0; i < count; i++) {
			if (treeIndex[i] == 0) {
				firstIndex++;
				maxFirstDepth = Math.max(maxFirstDepth, lengthFirst[firstIndex] = lengthFirst[vertex[i]] + 1);
				hashFirst[firstIndex] = hashFirst[vertex[i]] + power[lengthFirst[vertex[i]]] * label[i];
				hash.add(hashFirst[firstIndex]);
			} else {
				secondIndex++;
				lengthSecond[secondIndex] = lengthSecond[vertex[i]] + 1;
				parent[secondIndex] = vertex[i];
				hashSecond[secondIndex] = hashSecond[vertex[i]] * 43 + label[i];
			}
		}
		firstIndex = 0;
		secondIndex = 0;
		long answer = 1;
		for (int i = 0; i < count; i++) {
			if (treeIndex[i] == 0) {
				firstIndex++;
				answer += hash.addFirstGetSecond(hashFirst[firstIndex]);
			} else {
				secondIndex++;
				answer++;
				int current = secondIndex;
				int curDepth = 0;
				do {
					current = parent[current];
					long curHash = hashSecond[secondIndex] - hashSecond[current] * power[lengthSecond[secondIndex] - lengthSecond[current]];
					answer += hash.addSecondGetFirst(curHash);
					curDepth++;
				} while (current != 0 && curDepth < maxFirstDepth);
			}
			out.printLine(answer);
		}
	}
}

class Hash {
	int size = 1000007;
	int shift = 300001;
	boolean[] occupied = new boolean[size];
	long[] key = new long[size];
	int[] firstValue = new int[size];
	int[] secondValue = new int[size];

	void add(long key) {
		int index = (int) (key % size);
		if (index < 0)
			index += size;
		while (occupied[index] && this.key[index] != key) {
			index += shift;
			if (index >= size)
				index -= size;
		}
		occupied[index] = true;
		this.key[index] = key;
	}

	int addFirstGetSecond(long key) {
		int index = (int) (key % size);
		if (index < 0)
			index += size;
		while (occupied[index] && this.key[index] != key) {
			index += shift;
			if (index >= size)
				index -= size;
		}
		if (!occupied[index])
			return 0;
		firstValue[index]++;
		return secondValue[index];
	}

	int addSecondGetFirst(long key) {
		int index = (int) (key % size);
		if (index < 0)
			index += size;
		while (occupied[index] && this.key[index] != key) {
			index += shift;
			if (index >= size)
				index -= size;
		}
		if (!occupied[index])
			return 0;
		secondValue[index]++;
		return firstValue[index];
	}
}