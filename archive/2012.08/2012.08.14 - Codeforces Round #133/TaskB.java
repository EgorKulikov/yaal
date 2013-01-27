package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] first = new int[edgeCount];
		int[] second = new int[edgeCount];
		IOUtils.readIntArrays(in, first, second);
		MiscUtils.decreaseByOne(first, second);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (int i = 0; i < edgeCount; i++)
			setSystem.join(first[i], second[i]);
		int[] vertices = new int[count];
		int[] edges = new int[count];
		for (int i = 0; i < count; i++)
			vertices[setSystem.get(i)]++;
		for (int i : first)
			edges[setSystem.get(i)]++;
		int answer = 0;
		int odd = 0;
		for (int i = 0; i < count; i++) {
			if (vertices[i] == edges[i])
				answer += vertices[i] & 1;
			else
				odd += vertices[i] & 1;
		}
		answer += odd & 1;
		out.printLine(answer);
	}
}
