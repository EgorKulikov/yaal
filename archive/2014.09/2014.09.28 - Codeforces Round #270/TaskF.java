package net.egork;

import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntPair;
import net.egork.collections.intcollection.IntSet;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x = IOUtils.readIntArray(in, count);
		int[] y = IOUtils.readIntArray(in, count);
		List<IntPair> direct = simplify(x);
		List<IntPair> reverse = simplify(y);
		int at = 0;
		for (int i = 0; i < count; i++) {
			if (Integer.highestOneBit(x[i]) > Integer.highestOneBit(y[at])) {
				direct.add(new IntPair(i, i));
				x[i] = 0;
			} else {
				for (int j = 29; j >= 0; j--) {
					if ((x[i] >> j & 1) != (y[at] >> j & 1)) {
						for (int k = i + 1; k < count && x[k] != 0; k++) {
							if (Integer.highestOneBit(x[k]) == (1 << j)) {
								direct.add(new IntPair(i, k));
								x[i] ^= x[k];
								break;
							}
						}
						if ((x[i] >> j & 1) != (y[at] >> j & 1)) {
							out.printLine(-1);
							return;
						}
					}
				}
				if (i != at) {
					direct.add(new IntPair(at, at));
					direct.add(new IntPair(at, i));
					x[at] = x[i];
				}
				at++;
			}
		}
		for (int i = at; i < count; i++) {
			if (y[i] != 0) {
				out.printLine(-1);
				return;
			}
			direct.add(new IntPair(i, i));
			x[i] = 0;
		}
		Collections.reverse(reverse);
		direct.addAll(reverse);
		out.printLine(direct.size());
		for (IntPair pair : direct) {
			out.printLine(pair.first + 1, pair.second + 1);
		}
    }

	private List<IntPair> simplify(int[] x) {
		int id = 0;
		List<IntPair> result = new ArrayList<>();
		for (int i = 29; i >= 0 && id < x.length; i--) {
			if ((x[id] >> i & 1) == 0) {
				for (int j = id + 1; j < x.length; j++) {
					if ((x[j] >> i & 1) != 0) {
						result.add(new IntPair(id, j));
						x[id] ^= x[j];
						break;
					}
				}
			}
			if ((x[id] >> i & 1) != 0) {
				for (int j = 0; j < id; j++) {
					if ((x[j] >> i & 1) == 0) {
						result.add(new IntPair(j, id));
						x[j] ^= x[id];
					}
				}
				for (int j = id + 1; j < x.length; j++) {
					if ((x[j] >> i & 1) != 0) {
						result.add(new IntPair(j, id));
						x[j] ^= x[id];
					}
				}
				id++;
			}
		}
		return result;
	}
}
