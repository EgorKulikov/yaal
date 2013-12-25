package on2013_07.on2013_07_24_Codeforces_Round__193.E___Binary_Key;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.map.CPPMap;
import net.egork.collections.map.EHashMap;
import net.egork.misc.Factory;
import net.egork.string.SimpleStringHash;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Map;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] container = in.readLine(false).toCharArray();
		char[] message = in.readLine(false).toCharArray();
		int keyLength = in.readInt();
		int p = container.length / keyLength;
		char[][] parts = new char[keyLength][];
		int totalBig = container.length % keyLength;
		int totalSmall = keyLength - totalBig;
		Map<Long, IntList> map = new CPPMap<Long, IntList>(new Factory<IntList>() {
			public IntList create() {
				return new IntArrayList(1);
			}
		});
		for (int i = 0; i < keyLength; i++) {
			parts[i] = new char[p + (i < totalBig ? 1 : 0)];
			for (int j = 0; j < parts[i].length; j++)
				parts[i][j] = container[i + j * keyLength];
			map.get(new SimpleStringHash(new CharArray(parts[i])).hash(0)).add(i);
		}
		Map<Long, int[]> converted = new EHashMap<Long, int[]>();
		for (Map.Entry<Long, IntList> entry : map.entrySet()) {
			converted.put(entry.getKey(), entry.getValue().toArray());
		}
		String answer = null;
		for (int i = 1; i <= message.length; i++) {
			int big = message.length - p * i;
			int small = i - big;
			if (small < 0 || big < 0 || totalBig < big || totalSmall < small)
				continue;
			char[][] messageParts = new char[i][];
			long[] hash = new long[i];
			for (int j = 0; j < i; j++) {
				messageParts[j] = new char[p + (j < big ? 1 : 0)];
				for (int k = 0; k < messageParts[j].length; k++)
					messageParts[j][k] = message[j + i * k];
				hash[j] = new SimpleStringHash(new CharArray(messageParts[j])).hash(0);
			}
			char[] current = new char[keyLength];
			Arrays.fill(current, '0');
			int position = keyLength;
			boolean good = true;
			for (int j = i - 1; j >= 0; j--) {
				int[] array = converted.get(hash[j]);
				if (array == null) {
					good = false;
					break;
				}
				int next = Arrays.binarySearch(array, position - 1);
				if (next == -1) {
					good = false;
					break;
				}
				if (next < 0)
					next = -next - 2;
				position = array[next];
				current[position] = '1';
			}
			if (good) {
				String candidate = new String(current);
				if (answer == null || answer.compareTo(candidate) > 0)
					answer = candidate;
			}
		}
		if (answer == null)
			answer = "0";
		out.printLine(answer);
    }

	static class CharArray implements CharSequence {
		private final char[] array;

		CharArray(char[] array) {
			this.array = array;
		}

		public int length() {
			return array.length;
		}

		public char charAt(int index) {
			return array[index];
		}

		public CharSequence subSequence(int start, int end) {
			throw new UnsupportedOperationException();
		}
	}
}
