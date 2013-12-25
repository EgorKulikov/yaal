package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskJ {
	private static final int MOD = (int) 1e9;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		BigInteger money = in.readBigInteger();
		int direct = 0;
		int indirect = 2;
		int qtyDirect = 1;
		int qtyIndirect = 0;
		int size = money.bitLength();
		for (int i = 0; i < size + 2; i += 2) {
			int last = (money.testBit(i + 1) ? 2 : 0) + (money.testBit(i) ? 1 : 0);
			int nextDirect = Math.min(direct + last, indirect + last + 1);
			int nextQtyDirect = 0;
			if (direct + last == nextDirect)
				nextQtyDirect += qtyDirect;
			if (indirect + last + 1 == nextDirect)
				nextQtyDirect += qtyIndirect;
			if (nextQtyDirect >= MOD)
				nextQtyDirect -= MOD;
			int nextIndirect = Math.min(indirect + (3 - last), direct + (4 - last));
			int nextQtyIndirect = 0;
			if (nextIndirect == indirect + 3 - last)
				nextQtyIndirect += qtyIndirect;
			if (nextIndirect == direct + 4 - last)
				nextQtyIndirect += qtyDirect;
			if (nextQtyIndirect >= MOD)
				nextQtyIndirect -= MOD;
			direct = nextDirect;
			indirect = nextIndirect;
			qtyDirect = nextQtyDirect;
			qtyIndirect = nextQtyIndirect;
		}
		out.printLine(qtyDirect);
    }
}
