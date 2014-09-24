package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.PriorityQueue;
import java.util.Queue;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int washQty = in.readInt();
		int crunchQty = in.readInt();
		int dryQty = in.readInt();
		int washTime = in.readInt();
		int crunchTime = in.readInt();
		int dryTime = in.readInt();
		Queue<Integer> washers = new PriorityQueue<>();
		for (int i = 0; i < washQty; i++) {
			washers.add(0);
		}
		Queue<Integer> crunchers = new PriorityQueue<>();
		for (int i = 0; i < crunchQty; i++) {
			crunchers.add(0);
		}
		Queue<Integer> driers = new PriorityQueue<>();
		for (int i = 0; i < dryQty; i++) {
			driers.add(0);
		}
		int answer = 0;
		for (int i = 0; i < count; i++) {
			int washAt = washers.poll();
			int crunchAt = crunchers.poll();
			int dryAt = driers.poll();
			int start = Math.max(washAt, Math.max(crunchAt - washTime, dryAt - washTime - crunchTime));
			answer = start + washTime + crunchTime + dryTime;
			washers.add(start + washTime);
			crunchers.add(start + washTime + crunchTime);
			driers.add(answer);
		}
		out.printLine(answer);
    }
}
