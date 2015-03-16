package net.egork;

import net.egork.collections.intcollection.IntPair;
import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class Fencing {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		List<IntPair> points = new ArrayList<>();
		int count = in.readInt();
		int queryCount = in.readInt();
		for (int i = 0; i < count; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points.add(new IntPair(x, y));
		}
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			if (type == 1) {
				int x = in.readInt();
				int y = in.readInt();
				points.add(new IntPair(x, y));
			} else {
				long a = in.readInt();
				long b = in.readInt();
				long c = in.readLong();
				boolean more = false;
				boolean less = false;
				for (int j = 0; j < points.size(); j++) {
					long value = a * points.get(j).first + b * points.get(j).second - c;
					if (value >= 0) {
						more = true;
					}
					if (value <= 0) {
						less = true;
					}
				}
				out.printLine(more && less ? "NO" : "YES");
			}
		}
	}
}
