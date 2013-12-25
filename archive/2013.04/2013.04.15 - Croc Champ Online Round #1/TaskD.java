package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		IntList leftToRight = new IntArrayList(count + 1);
		leftToRight.add(-1);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (int i = 0; i < edgeCount; i++) {
			if (setSystem.join(from[i], to[i]))
				leftToRight.add(i);
		}
		if (leftToRight.back() != edgeCount - 1)
			leftToRight.add(edgeCount - 1);
		IntList rightToLeft = new IntArrayList(count);
		rightToLeft.add(edgeCount);
		setSystem = new RecursiveIndependentSetSystem(count);
		for (int i = edgeCount - 1; i >= 0; i--) {
			if (setSystem.join(from[i], to[i]))
				rightToLeft.add(i);
		}
		if (rightToLeft.back() != 0)
			rightToLeft.add(0);
		int[] leftIndex = new int[edgeCount];
		int[] ltrArray = leftToRight.toArray();
		for (int i = 1; i < ltrArray.length; i++) {
			for (int j = ltrArray[i - 1] + 1; j <= ltrArray[i]; j++)
				leftIndex[j] = i - 1;
		}
		int[] rightIndex = new int[edgeCount];
		int[] rtlArray = rightToLeft.toArray();
		for (int i = 1; i < rtlArray.length; i++) {
			for (int j = rtlArray[i]; j < rtlArray[i - 1]; j++)
				rightIndex[j] = i - 1;
		}
		int[][] answer = new int[count][count];
		setSystem = new RecursiveIndependentSetSystem(count);
		for (int i = 0; i < ltrArray.length - 1; i++) {
			IndependentSetSystem inwardSystem = new RecursiveIndependentSetSystem((RecursiveIndependentSetSystem)setSystem);
			for (int j = 0; j < rtlArray.length - 1; j++) {
				answer[i][j] = inwardSystem.getSetCount();
				inwardSystem.join(from[rtlArray[j + 1]], to[rtlArray[j + 1]]);
			}
			setSystem.join(from[ltrArray[i + 1]], to[ltrArray[i + 1]]);
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int left = in.readInt() - 1;
			int right = in.readInt() - 1;
			out.printLine(answer[leftIndex[left]][rightIndex[right]]);
		}
    }
}
