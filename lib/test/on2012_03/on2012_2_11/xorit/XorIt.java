package on2012_03.on2012_2_11.xorit;



import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class XorIt {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int qty = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		Arrays.sort(array);
		for (int i = 0; i <= 31; i++) {
			int mask = -(1 << i);
			int cnt = 0;
			int same = 1;
			for (int j = 1; j < count; j++) {
				if ((array[j] & mask) == (array[j - 1] & mask))
					cnt += same++;
				else
					same = 1;
			}
			if (cnt < qty)
				continue;
			if (i == 0) {
				int[] answer = new int[qty];
				out.printLine(Array.wrap(answer).subList(0, qty).toArray());
				return;
			}
			int[] answer = new int[cnt];
			int start = 0;
			int index = 0;
			for (int j = 1; j <= count; j++) {
				if (j < count && (array[j] & mask) == (array[j - 1] & mask))
					continue;
				for (int k = start; k < j; k++) {
					for (int l = k + 1; l < j; l++)
						answer[index++] = array[k] ^ array[l];
				}
				start = j;
			}
			Arrays.sort(answer);
			out.printLine(Array.wrap(answer).subList(0, qty).toArray());
			return;
		}
	}
}
