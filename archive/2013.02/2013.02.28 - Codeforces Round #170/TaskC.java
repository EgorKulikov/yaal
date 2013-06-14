package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.map.CPPMap;
import net.egork.collections.map.EHashMap;
import net.egork.io.IOUtils;
import net.egork.misc.Factory;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int cutCount = in.readInt();
		final int[] x0 = new int[cutCount];
		final int[] y0 = new int[cutCount];
		final int[] x1 = new int[cutCount];
		final int[] y1 = new int[cutCount];
		IOUtils.readIntArrays(in, x0, y0, x1, y1);
		Map<Integer, IntList> horizontal = new CPPMap<Integer, IntList>(new Factory<IntList>() {
			public IntList create() {
				return new IntArrayList();
			}
		});
		Map<Integer, IntList> vertical = new CPPMap<Integer, IntList>(new Factory<IntList>() {
			public IntList create() {
				return new IntArrayList();
			}
		});
		for (int i = 0; i < cutCount; i++) {
			if (x0[i] == x1[i])
				horizontal.get(x0[i]).add(i);
			else
				vertical.get(y0[i]).add(i);
		}
		Map<Integer, Integer> horizontalQty = new EHashMap<Integer, Integer>();
		Map<Integer, Integer> verticalQty = new EHashMap<Integer, Integer>();
		for (Map.Entry<Integer, IntList> entry : horizontal.entrySet()) {
			entry.getValue().inPlaceSort(new IntComparator() {
				public int compare(int first, int second) {
					return Math.min(y0[first], y1[first]) - Math.min(y0[second], y1[second]);
				}
			});
			int end = 0;
			int qty = 0;
			for (int i : entry.getValue().toArray()) {
				int curStart = Math.min(y0[i], y1[i]);
				if (curStart > end)
					qty += curStart - end;
				end = Math.max(end, Math.max(y0[i], y1[i]));
			}
			qty += columnCount - end;
			horizontalQty.put(entry.getKey(), qty);
		}
		for (Map.Entry<Integer, IntList> entry : vertical.entrySet()) {
			entry.getValue().inPlaceSort(new IntComparator() {
				public int compare(int first, int second) {
					return Math.min(x0[first], x1[first]) - Math.min(x0[second], x1[second]);
				}
			});
			int end = 0;
			int qty = 0;
			for (int i : entry.getValue().toArray()) {
				int curStart = Math.min(x0[i], x1[i]);
				if (curStart > end)
					qty += curStart - end;
				end = Math.max(end, Math.max(x0[i], x1[i]));
			}
			qty += rowCount - end;
			verticalQty.put(entry.getKey(), qty);
		}
		int nimber = 0;
		for (int i : horizontalQty.values())
			nimber ^= i;
		for (int i : verticalQty.values())
			nimber ^= i;
		nimber ^= columnCount * ((rowCount - horizontalQty.size() - 1) & 1);
		nimber ^= rowCount * ((columnCount - verticalQty.size() - 1) & 1);
		if (nimber == 0) {
			out.printLine("SECOND");
			return;
		}
		out.printLine("FIRST");
		for (int i = 1; i < rowCount; i++) {
			if (!horizontalQty.containsKey(i)) {
				horizontalQty.put(i, columnCount);
				break;
			}
		}
		for (int i = 1; i < columnCount; i++) {
			if (!verticalQty.containsKey(i)) {
				verticalQty.put(i, rowCount);
				break;
			}
		}
		for (Map.Entry<Integer, Integer> entry : horizontalQty.entrySet()) {
			if ((nimber ^ entry.getValue()) <= entry.getValue()) {
				int remaining = entry.getValue() - (nimber ^ entry.getValue());
				int end = 0;
				for (int i : horizontal.get(entry.getKey()).toArray()) {
					int curStart = Math.min(y0[i], y1[i]);
					if (curStart > end) {
						if (remaining <= curStart - end) {
							out.printLine(entry.getKey(), 0, entry.getKey(), end + remaining);
							return;
						}
						remaining -= curStart - end;
					}
					end = Math.max(end, Math.max(y0[i], y1[i]));
				}
				out.printLine(entry.getKey(), 0, entry.getKey(), end + remaining);
				return;
			}
		}
		for (Map.Entry<Integer, Integer> entry : verticalQty.entrySet()) {
			if ((nimber ^ entry.getValue()) <= entry.getValue()) {
				int remaining = entry.getValue() - (nimber ^ entry.getValue());
				int end = 0;
				for (int i : vertical.get(entry.getKey()).toArray()) {
					int curStart = Math.min(x0[i], x1[i]);
					if (curStart > end) {
						if (remaining <= curStart - end) {
							out.printLine(0, entry.getKey(), end + remaining, entry.getKey());
							return;
						}
						remaining -= curStart - end;
					}
					end = Math.max(end, Math.max(x0[i], x1[i]));
				}
				out.printLine(0, entry.getKey(), end + remaining, entry.getKey());
				return;
			}
		}
    }
}
