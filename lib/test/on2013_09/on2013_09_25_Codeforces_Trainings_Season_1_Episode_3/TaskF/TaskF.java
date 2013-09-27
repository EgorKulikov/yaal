package on2013_09.on2013_09_25_Codeforces_Trainings_Season_1_Episode_3.TaskF;



import net.egork.collections.set.EHashSet;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Set;

public class TaskF {
	Set<Long> set = new EHashSet<Long>();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long start = 0;
		for (int i = 0; i < 4; i++) {
			int row = in.readInt() - 1;
			int column = in.readInt() - 1;
			start += 1L << ((row * 8 + column));
		}
		long finish = 0;
		for (int i = 0; i < 4; i++) {
			int row = in.readInt() - 1;
			int column = in.readInt() - 1;
			finish += 1L << ((row * 8 + column));
		}
		go(start, 4, false);
		if (go(finish, 4, true))
			out.printLine("YES");
		else
			out.printLine("NO");
    }

	final boolean go(long mask, int moves, boolean check) {
		if (check) {
			if (set.contains(mask))
				return true;
		} else
			set.add(mask);
		if (moves == 0)
			return false;
		for (int i = 0; i < 64; i++) {
			if ((mask >> i & 1) == 1) {
				int row = i >> 3;
				int column = i & 7;
				for (int j = 0; j < 4; j++) {
					int nRow = row + MiscUtils.DX4[j];
					int nColumn = column + MiscUtils.DY4[j];
					int at = nRow * 8 + nColumn;
					if (MiscUtils.isValidCell(nRow, nColumn, 8, 8)) {
						if ((mask >> at & 1) == 0 && go(mask - (1L << i) + (1L << at), moves - 1, check))
							return true;
						if ((mask >> at & 1) == 1) {
							nRow += MiscUtils.DX4[j];
							nColumn += MiscUtils.DY4[j];
							at = nRow * 8 + nColumn;
							if (MiscUtils.isValidCell(nRow, nColumn, 8, 8) && (mask >> at & 1) == 0 && go(mask - (1L << i) + (1L << at), moves - 1, check))
								return true;
						}
					}
				}
			}
		}
		return false;
	}
}
