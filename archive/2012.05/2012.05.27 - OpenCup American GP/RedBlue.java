package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class RedBlue {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int vertexCount = in.readInt();
		int edgeCount = in.readInt();
		int mustBeGreen = in.readInt();
		if (vertexCount == 0)
			throw new UnknownError();
		boolean[] isGreen = new boolean[edgeCount];
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		for (int i = 0; i < edgeCount; i++) {
			isGreen[i] = in.readCharacter() == 'B';
			from[i] = in.readInt() - 1;
			to[i] = in.readInt() - 1;
		}
		IndependentSetSystem red = new IndependentSetSystem(vertexCount);
		IndependentSetSystem green = new IndependentSetSystem(vertexCount);
		int redComponents = vertexCount;
		int greenEdges = 0;
		for (int i = 0; i < edgeCount; i++) {
			if (isGreen[i]) {
				if (green.join(from[i], to[i]))
					greenEdges++;
			} else {
				if (red.join(from[i], to[i]))
					redComponents--;
			}
		}
		if (redComponents - 1 > mustBeGreen || greenEdges < mustBeGreen)
			out.printLine(0);
		else
			out.printLine(1);
	}
}

class IndependentSetSystem {
	int[] parent;

	IndependentSetSystem(int size) {
		parent = new int[size];
		for (int i = 0; i < size; i++)
			parent[i] = i;
	}

	boolean join(int a, int b) {
		a = get(a);
		b = get(b);
		if (a == b)
			return false;
		parent[a] = b;
		return true;
	}

	int get(int v) {
		if (v == parent[v])
			return v;
		return parent[v] = get(parent[v]);
	}
}