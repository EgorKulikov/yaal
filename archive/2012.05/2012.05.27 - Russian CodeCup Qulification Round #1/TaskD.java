import net.egork.collections.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.string.AbstractStringHash;
import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TaskD {
	long[] power = new long[2001];
	private StringHash[] hashes;
	private int size;

	{
		power[0] = 1;
		for (int i = 1; i <= 2000; i++)
			power[i] = power[i - 1] * AbstractStringHash.MULTIPLIER;
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Set<Long> set = new HashSet<Long>();
		for (int i = 0; i < count; i++)
			set.add(new SimpleStringHash(in.readString()).hash(0));
		int rowCount = in.readInt();
		String[] text = IOUtils.readStringArray(in, rowCount);
		size = text[0].length();
		hashes = new StringHash[rowCount];
		for (int i = 0; i < rowCount; i++)
			hashes[i] = new SimpleStringHash(text[i]);
		Set<Integer> variants = new HashSet<Integer>(Array.wrap(ArrayUtils.range(0, size - 1)));
		int[][] end = new int[rowCount][size];
		int[][] start = new int[rowCount][size];
		for (int k = 0; k < rowCount; k++) {
			String s = text[k];
			s += s;
			int curEnd = -1;
			for (int i = 2 * size - 1; i >= size; i--) {
				if (s.charAt(i) == '.')
					curEnd = i;
			}
			for (int i = size - 1; i >= 0; i--) {
				if (s.charAt(i) == '.')
					curEnd = i;
				end[k][i] = curEnd;
			}
			int curBegin = -1;
			for (int i = 0; i < size; i++) {
				if (s.charAt(i) == '.')
					curBegin = i + 1;
			}
			for (int i = size; i < 2 * size; i++) {
				start[k][i - size] = curBegin - size;
				if (s.charAt(i) == '.')
					curBegin = i + 1;
			}
			for (int i = 1; i <= size; i++) {
				if (s.charAt(i) == '.' || s.charAt(i - 1) != '.')
					continue;
				int to;
				for (int j = i; ; j++) {
					if (s.charAt(j) == '.') {
						to = j;
						break;
					}
				}
				long curHash = getHash(k, i, to);
				if (!set.contains(curHash)) {
					Set<Integer> newVariants = new HashSet<Integer>();
					for (int j = i; j <= to; j++) {
						if (variants.contains(j % size))
							newVariants.add(j % size);
					}
					variants = newVariants;
				}
			}
		}
		set.add(0L);
		Set<Integer> answer = new TreeSet<Integer>();
		for (int position : variants) {
			if (end[0][position] != position && !set.contains(getHash(0, position, end[0][position])))
				continue;
			if (start[rowCount - 1][position] != position && !set.contains(getHash(rowCount - 1, start[rowCount - 1][position], position)))
				continue;
			boolean good = true;
			for (int i = 1; i < rowCount; i++) {
				long curHash = getHash(i - 1, start[i - 1][position], position) + power[position - start[i - 1][position]] * getHash(i, position, end[i][position]);
				if (!set.contains(curHash)) {
					good = false;
					break;
				}
			}
			if (good)
				answer.add(position);
		}
		out.printLine(answer.size());
		out.printLine(answer.toArray());
	}

	long getHash(int index, int from, int to) {
		if (from < 0) {
			from += size;
			to += size;
		}
		if (to <= size)
			return hashes[index].hash(from, to);
		else
			return hashes[index].hash(from, size) + power[size - from] * hashes[index].hash(0, to - size);
	}
}
