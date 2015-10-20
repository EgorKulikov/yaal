package on2015_02.on2015_02_20_USACO_2015_February_Contest__Gold.Fencing;


import net.egork.generated.collections.pair.IntIntPair;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class Fencing {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		List<IntIntPair> points = new ArrayList<>();
		int count = in.readInt();
		int queryCount = in.readInt();
		for (int i = 0; i < count; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points.add(new IntIntPair(x, y));
		}
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			if (type == 1) {
				int x = in.readInt();
				int y = in.readInt();
				points.add(new IntIntPair(x, y));
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
