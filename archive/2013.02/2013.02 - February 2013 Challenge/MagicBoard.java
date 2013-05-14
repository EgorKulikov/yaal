package net.egork;

import net.egork.collections.set.TreapSet;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NavigableSet;

public class MagicBoard {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		final int[] lastZeroRow = new int[count + 1];
		final int[] lastOneRow = new int[count + 1];
		final int[] lastZeroColumn = new int[count + 1];
		final int[] lastOneColumn = new int[count + 1];
		Arrays.fill(lastZeroRow, -1);
		Arrays.fill(lastOneRow, -1);
		Arrays.fill(lastZeroColumn, -1);
		Arrays.fill(lastOneColumn, -1);
		NavigableSet<Integer> zeroRows = new TreapSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return lastZeroRow[o1] - lastZeroRow[o2];
			}
		});
		NavigableSet<Integer> oneRows = new TreapSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return lastOneRow[o1] - lastOneRow[o2];
			}
		});
		NavigableSet<Integer> zeroColumns = new TreapSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return lastZeroColumn[o1] - lastZeroColumn[o2];
			}
		});
		NavigableSet<Integer> oneColumns = new TreapSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return lastOneColumn[o1] - lastOneColumn[o2];
			}
		});
		for (int i = 0; i < queryCount; i++) {
			char lineType = in.readCharacter();
			in.readCharacter();
			in.readCharacter();
			char queryType = in.readCharacter();
			in.readCharacter();
			in.readCharacter();
			if (queryType == 'Q') {
				in.readCharacter();
				in.readCharacter();
			}
			int line = in.readInt() - 1;
			if (queryType == 'S') {
				int value = in.readInt();
				if (lineType == 'R') {
					if (lastZeroRow[line] > lastOneRow[line])
						zeroRows.remove(line);
					else if (lastOneRow[line] > lastZeroRow[line])
						oneRows.remove(line);
					if (value == 0) {
						lastZeroRow[line] = i;
						zeroRows.add(line);
					} else {
						lastOneRow[line] = i;
						oneRows.add(line);
					}
				} else {
					if (lastZeroColumn[line] > lastOneColumn[line])
						zeroColumns.remove(line);
					else if (lastOneColumn[line] > lastZeroColumn[line])
						oneColumns.remove(line);
					if (value == 0) {
						lastZeroColumn[line] = i;
						zeroColumns.add(line);
					} else {
						lastOneColumn[line] = i;
						oneColumns.add(line);
					}
				}
			} else {
				if (lineType == 'R') {
					if (lastOneRow[line] <= lastZeroRow[line]) {
						lastOneColumn[count] = lastZeroRow[line];
						out.printLine(count - oneColumns.tailSet(count).size());
					} else {
						lastZeroColumn[count] = lastOneRow[line];
						out.printLine(zeroColumns.tailSet(count).size());
					}
				} else {
					if (lastOneColumn[line] <= lastZeroColumn[line]) {
						lastOneRow[count] = lastZeroColumn[line];
						out.printLine(count - oneRows.tailSet(count).size());
					} else {
						lastZeroRow[count] = lastOneColumn[line];
						out.printLine(zeroRows.tailSet(count).size());
					}
				}
			}
		}
	}
}
