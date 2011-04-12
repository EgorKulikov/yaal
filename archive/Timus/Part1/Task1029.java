package Timus.Part1;

import net.egork.arrays.ArrayWrapper;
import net.egork.arrays.ArrayUtils;
import net.egork.collections.CollectionUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task1029 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int floorCount = in.readInt();
		int officialPerFloor = in.readInt();
		int[][] costs = in.readIntTable(floorCount, officialPerFloor);
		long[] lastFloorCosts = new long[officialPerFloor];
		int[][] lastOfficialVisited = new int[floorCount][officialPerFloor];
		int index = 0;
		for (int[] currentCosts : costs) {
			long[] sum = new long[officialPerFloor + 1];
			for (int i = 0; i < officialPerFloor; i++)
				sum[i + 1] = sum[i] + currentCosts[i];
			long[] thisFloorCosts = new long[officialPerFloor];
			for (int i = 0; i < officialPerFloor; i++) {
				thisFloorCosts[i] = Long.MAX_VALUE;
				for (int j = 0; j < officialPerFloor; j++) {
					long currentCost = lastFloorCosts[j] + sum[Math.max(i, j) + 1] - sum[Math.min(i, j)];
					if (thisFloorCosts[i] > currentCost) {
						thisFloorCosts[i] = currentCost;
						lastOfficialVisited[index][i] = j;
					}
				}
			}
			lastFloorCosts = thisFloorCosts;
			index++;
		}
		index = ArrayUtils.minIndex(ArrayWrapper.wrap(lastFloorCosts));
		int floor = floorCount - 1;
		List<Integer> answer = new ArrayList<Integer>();
		while (floor != 0) {
			answer.addAll(CollectionUtils.range(index + 1, lastOfficialVisited[floor][index] + 1));
			index = lastOfficialVisited[floor][index];
			floor--;
		}
		answer.add(index + 1);
		Collections.reverse(answer);
		IOUtils.printCollection(answer, out);
	}
}

