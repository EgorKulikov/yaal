package on2015_06.on2015_06_June_Challenge_2015.Anticommutative_implication;


import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class AnticommutativeImplication {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int size = in.readInt();
        int[][] a = IOUtils.readIntTable(in, size, size);
        int[] color = new int[size];
        int[] queue = new int[size];
        List<IntIntPair> values = new ArrayList<>();
        List<IntList> indices = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (color[i] != 0) {
                continue;
            }
            color[i] = 1;
            queue[0] = i;
            int qSize = 1;
            IntList list = new IntArrayList();
            list.add(i);
            int ones = 1;
            int zeroes = 0;
            for (int j = 0; j < qSize; j++) {
                int current = queue[j];
                for (int k = 0; k < size; k++) {
                    if (a[current][k] == 1) {
                        if (color[k] == color[current]) {
                            out.printLine(-1);
                            return;
                        }
                        if (color[k] == 0) {
                            color[k] = -color[current];
                            queue[qSize++] = k;
                            list.add(k);
                            if (color[k] == 1) {
                                ones++;
                            } else {
                                zeroes++;
                            }
                        }
                    }
                }
            }
            values.add(new IntIntPair(ones, zeroes));
            indices.add(list);
        }
        int[][] best = new int[values.size() + 1][size + 1];
		int[][] last = new int[values.size() + 1][size + 1];
        int total = 0;
		ArrayUtils.fill(best, Integer.MIN_VALUE);
		best[0][0] = 0;
        for (int i = 0; i < values.size(); i++) {
			IntIntPair current = values.get(i);
			for (int j = 0; j <= total; j++) {
				if (best[i][j] != Integer.MIN_VALUE) {
					int value = best[i][j] + j * current.second + (total - j) * current.first;
					if (best[i + 1][j + current.first] < value) {
						best[i + 1][j + current.first] = value;
						last[i + 1][j + current.first] = 1;
					}
					value = best[i][j] + j * current.first + (total - j) * current.second;
					if (best[i + 1][j + current.second] < value) {
						best[i + 1][j + current.second] = value;
						last[i + 1][j + current.second] = -1;
					}
				}
			}
			total += current.first + current.second;
		}
		int at = ArrayUtils.maxPosition(best[values.size()]);
		int[] answer = new int[size];
		for (int i = values.size(); i > 0; i--) {
			for (int j = 0; j < indices.get(i - 1).size(); j++) {
				int position = indices.get(i - 1).get(j);
				answer[position] = color[position] * last[i][at];
			}
			if (last[i][at] == 1) {
				at -= values.get(i - 1).first;
			} else {
				at -= values.get(i - 1).second;
			}
		}
		for (int i = 0; i < size; i++) {
			if (answer[i] == -1) {
				answer[i] = 0;
			}
		}
		out.printLine(answer);
	}
}
