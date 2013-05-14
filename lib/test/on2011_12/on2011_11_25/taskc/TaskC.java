package on2011_12.on2011_11_25.taskc;



import net.egork.misc.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int treeCount = in.readInt();
		int mushroomCount = in.readInt();
		final long[] treePosition = new long[treeCount];
		final long[] height = new long[treeCount];
		long[] left = new long[treeCount];
		long[] right = new long[treeCount];
		IOUtils.readLongArrays(in, treePosition, height, left, right);
		int[] mushroomPosition = new int[mushroomCount];
		int[] power = new int[mushroomCount];
		IOUtils.readIntArrays(in, mushroomPosition, power);
		Integer[] treeOrder = ListUtils.order(Array.wrap(treePosition));
		Integer[] rightTreeOrder = ArrayUtils.order(treeCount, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return IntegerUtils.longCompare(treePosition[o1] + height[o1], treePosition[o2] + height[o2]);
			}
		});
		Integer[] mushroomOrder = ListUtils.order(Array.wrap(mushroomPosition));
		int newTreeIndex = 0;
		int oldTreeIndex = 0;
		double[] alive = new double[mushroomCount];
		Arrays.fill(alive, 1);
		double currentAlive = 0;
		int countShould = 0;
		for (int i : mushroomOrder) {
			while (newTreeIndex < treeCount && treePosition[treeOrder[newTreeIndex]] < mushroomPosition[i]) {
				if (right[treeOrder[newTreeIndex]] == 100)
					countShould++;
				else
					currentAlive += Math.log((100 - right[treeOrder[newTreeIndex]]) / 100.);
				newTreeIndex++;
			}
			while (oldTreeIndex < treeCount && treePosition[rightTreeOrder[oldTreeIndex]] + height[rightTreeOrder[oldTreeIndex]] < mushroomPosition[i]) {
				if (right[rightTreeOrder[oldTreeIndex]] == 100)
					countShould--;
				else
					currentAlive -= Math.log((100 - right[rightTreeOrder[oldTreeIndex]]) / 100.);
				oldTreeIndex++;
			}
			if (countShould != 0)
				alive[i] = 0;
			else
				alive[i] *= Math.exp(currentAlive);
		}
		Collections.reverse(Array.wrap(mushroomOrder));
		Collections.reverse(Array.wrap(treeOrder));
		Integer[] leftTreeOrder = ArrayUtils.order(treeCount, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return -IntegerUtils.longCompare(treePosition[o1] - height[o1], treePosition[o2] - height[o2]);
			}
		});
		currentAlive = 0;
		countShould = 0;
		newTreeIndex = 0;
		oldTreeIndex = 0;
		for (int i : mushroomOrder) {
			while (newTreeIndex < treeCount && treePosition[treeOrder[newTreeIndex]] > mushroomPosition[i]) {
				if (left[treeOrder[newTreeIndex]] == 100)
					countShould++;
				else
					currentAlive += Math.log((100 - left[treeOrder[newTreeIndex]]) / 100.);
				newTreeIndex++;
			}
			while (oldTreeIndex < treeCount && treePosition[leftTreeOrder[oldTreeIndex]] - height[leftTreeOrder[oldTreeIndex]] > mushroomPosition[i]) {
				if (left[leftTreeOrder[oldTreeIndex]] == 100)
					countShould--;
				else
					currentAlive -= Math.log((100 - left[leftTreeOrder[oldTreeIndex]]) / 100.);
				oldTreeIndex++;
			}
			if (countShould != 0)
				alive[i] = 0;
			else
				alive[i] *= Math.exp(currentAlive);
		}
		double answer = 0;
		for (int i = 0; i < mushroomCount; i++)
			answer += alive[i] * power[i];
		out.printLine(answer);
	}
}
