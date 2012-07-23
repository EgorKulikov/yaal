package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class Race {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		final double[] angle = new double[count];
		for (int i = 0; i < count; i++)
			angle[i] = in.readDouble();
//		NavigableSet<Integer> toLeft = new TreeSet<Integer>();
//		NavigableSet<Integer> toRight = new TreeSet<Integer>();
		int leftCount = 0;
		int rightCount = 0;
		for (int i = 0; i < count; i++) {
			if (angle[i] < Math.PI / 2)
				rightCount++;
			else
				leftCount++;
		}
		int[] left = new int[leftCount];
		int[] right = new int[rightCount];
		int leftIndex = 0;
		int rightIndex = 0;
		for (int i = 0; i < count; i++) {
			if (angle[i] < Math.PI / 2)
				right[rightIndex++] = i;
			else
				left[leftIndex++] = i;
		}
		Integer[] order = new Integer[count];
		for (int i = 0; i < count; i++)
			order[i] = i;
		Arrays.sort(order, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return Double.compare(Math.abs(angle[o1] - Math.PI / 2), Math.abs(angle[o2] - Math.PI / 2));
			}
		});
		boolean[] isWinner = new boolean[count];
		boolean[] bad = new boolean[count];
		leftIndex = leftCount;
		rightIndex = 0;
		int start = 0;
		int answer = 0; 
		for (int i = 0; i < count; i++) {
			isWinner[order[i]] = !bad[order[i]];
			if (isWinner[order[i]])
				answer++;
			if (i == count - 1 || angle[order[i]] != angle[order[i + 1]]) {
				for (int j = start; j <= i; j++) {
//					toLeft.removeAll(new ArrayList<Integer>(toLeft.tailSet(order[j], true)));
//					toRight.removeAll(new ArrayList<Integer>(toRight.headSet(order[j], true)));
					int newLeftIndex = Arrays.binarySearch(left, order[j]);
					if (newLeftIndex < 0)
						newLeftIndex = -newLeftIndex - 1;
					for (int k = newLeftIndex; k < leftIndex; k++)
						bad[left[k]] = true;
					leftIndex = Math.min(newLeftIndex, leftIndex);
					int newRightIndex = Arrays.binarySearch(right, order[j]);
					if (newRightIndex < 0)
						newRightIndex = -newRightIndex - 1;
					else
						newRightIndex++;
					for (int k = rightIndex; k < newRightIndex; k++)
						bad[right[k]] = true;
					rightIndex = Math.max(rightIndex, newRightIndex);
				}
				start = i + 1;
			}
		}
		out.printLine(answer);
		boolean first = true;
		for (int i = 0; i < count; i++) {
			if (isWinner[i]) {
				if (!first)
					out.print(" ");
				else
					first = false;
				out.print(i + 1);
			}
		}
		out.printLine();
	}
}
