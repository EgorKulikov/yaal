package on2013_07.on2013_07_21_Yandex_Algorithm_Online_Round__3.TaskD;



import net.egork.io.IOUtils;
import net.egork.string.AbstractStringHash;
import net.egork.string.SimpleStringHash;
import net.egork.string.StringHash;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[] words = IOUtils.readStringArray(in, count);
		StringHash[] hashes = new StringHash[count];
		long[] full = new long[count];
		for (int i = 0; i < count; i++) {
			hashes[i] = new SimpleStringHash(words[i]);
			full[i] = hashes[i].hash(0);
		}
		Arrays.sort(full);
		long[] counter = new long[1000000];
		int k = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 1; j < words[i].length(); j++) {
				if (Arrays.binarySearch(full, hashes[i].hash(0, j)) >= 0)
					counter[k++] = hashes[i].hash(j);
			}
		}
		Arrays.sort(counter, 0, k);
		long answer = 0;
		long last = Long.MIN_VALUE;
		long add = 1;
		for (int i = 0; i < k; i++) {
			long value = counter[i];
			if (last == value)
				answer += add++;
			else {
				last = value;
				add = 1;
			}
		}
		out.printLine(answer);
    }

	static class SimpleStringHash2 extends AbstractStringHash {
		static final long REVERSE = BigInteger.valueOf(103).modInverse(BigInteger.ONE.shiftLeft(64)).longValue();

		long[] hash;
		long[] reverse;

		SimpleStringHash2(String s) {
			int length = s.length();
			hash = new long[length + 1];
			reverse = new long[length + 1];
			reverse[0] = 1;
			long power = 1;
			for (int i = 0; i < length; i++) {
				hash[i + 1] = hash[i] + s.charAt(i) * power;
				power *= 103;
				reverse[i + 1] = reverse[i] * REVERSE;
			}
		}


		public long hash(int from, int to) {
			return (hash[to] - hash[from]) * reverse[from];
		}

		public int length() {
			return hash.length - 1;
		}
	}
}
