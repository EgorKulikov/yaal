package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] transfers = IOUtils.readIntArray(in, count);
		long[] speed = IOUtils.readLongArray(in, 2);
		long transferSpeed = in.readInt();
		int fromBuilding = in.readInt() - 1;
		int fromFloor = in.readInt();
		int toBuilding = in.readInt() - 1;
		int toFloor = in.readInt();
		long answer = Long.MAX_VALUE;
		int[] nearFrom = near(transfers, fromFloor);
		int[] nearTo = near(transfers, toFloor);
		if (fromBuilding == toBuilding) {
			answer = Math.abs(fromFloor - toFloor) * speed[fromBuilding];
			for (int f1 : nearFrom) {
				for (int f2 : nearTo)
					answer = Math.min(answer, (Math.abs(f1 - fromFloor) + Math.abs(f2 - toFloor)) * speed[fromBuilding] + Math.abs(f1 - f2) * speed[1 - fromBuilding] + 2 * transferSpeed);
			}
		} else {
			int[] all = new int[4];
			System.arraycopy(nearFrom, 0, all, 0, 2);
			System.arraycopy(nearTo, 0, all, 2, 2);
			for (int i : all)
				answer = Math.min(answer, Math.abs(i - fromFloor) * speed[fromBuilding] + Math.abs(i - toFloor) * speed[toBuilding] + transferSpeed);
		}
		out.printLine(answer);
    }

	private int[] near(int[] transfers, int floor) {
		int position = Arrays.binarySearch(transfers, floor);
		if (position < 0)
			position = Math.max(0, -position - 2);
		return new int[]{transfers[position], transfers[Math.min(position + 1, transfers.length - 1)]};
	}
}
