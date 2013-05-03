package on2013_03.on2013_03_03_ByteCode.PartyTime;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class PartyTime {
	int count;
	int[] a;
	int[] b;
	int[] qty;
	int[][][][] result;
	int[] rearrange;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		count = in.readInt();
		a = IOUtils.readIntArray(in, count + 1);
		b = IOUtils.readIntArray(in, count + 1);
		qty = IOUtils.readIntArray(in, count);
		int total = 0;
		for (int i : qty)
			total += i;
		result = new int[count + 1][count][2][total + 1];
		rearrange = new int[count + 1];
		Arrays.fill(rearrange, -1);
		ArrayUtils.fill(result, -1);
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i <= qty[0]; i++)
			answer = Math.min(answer, go(1, 0, i, 0, qty[0] - i));
		out.printLine(answer);
    }

	private int go(int floor, int lastA, int peopleA, int lastB, int peopleB) {
		int key1 = Math.min(lastA, lastB);
		int key2 = lastA > lastB ? 1 : 0;
		int key3 = key2 == 1 ? peopleB : peopleA;
		if (result[floor][key1][key2][key3] != -1)
			return result[floor][key1][key2][key3];
		if (floor == count) {
			int total = 0;
			if (peopleA != 0)
				total += a[lastA] ^ a[count] ^ peopleA;
			if (peopleB != 0)
				total += b[lastB] ^ b[count] ^ peopleB;
			return result[floor][key1][key2][key3] = total;
		}
		int answer = Integer.MAX_VALUE;
		answer = Math.min(answer, (a[lastA] ^ a[floor] ^ peopleA) + go(floor + 1, floor, peopleA + qty[floor], lastB, peopleB));
		answer = Math.min(answer, (b[lastB] ^ b[floor] ^ peopleB) + go(floor + 1, lastA, peopleA, floor, peopleB + qty[floor]));
		if (rearrange[floor] == -1) {
			rearrange[floor] = Integer.MAX_VALUE;
			for (int i = 0; i <= peopleA + peopleB + qty[floor]; i++)
				rearrange[floor] = Math.min(rearrange[floor], go(floor + 1, floor, i, floor, peopleB + peopleA + qty[floor] - i));
		}
		answer = Math.min(answer, (a[lastA] ^ a[floor] ^ peopleA) + (b[lastB] ^ b[floor] ^ peopleB) + rearrange[floor]);
		return result[floor][key1][key2][key3] = answer;
	}
}
