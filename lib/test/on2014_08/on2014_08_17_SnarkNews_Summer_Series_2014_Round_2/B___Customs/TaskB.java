package on2014_08.on2014_08_17_SnarkNews_Summer_Series_2014_Round_2.B___Customs;



import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.SumIntervalTree;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int willExit = in.readInt();
		BigInteger passCost = in.readBigInteger();
		BigInteger waitCost = in.readBigInteger();
		String[] order = IOUtils.readStringArray(in, willExit);
		boolean[][] occupied = new boolean[count][6];
//		for (int i = 0; i < willExit; i++) {
//			occupied[Integer.parseInt(order[i].substring(0, order[i].length() - 1)) - 1][order[i].charAt(order[i].length() - 1) - 'A'] = true;
//		}
		ArrayUtils.fill(occupied, true);
		IntervalTree tree = new SumIntervalTree(count);
		for (int i = 0; i < count; i++) {
			int total = (occupied[i][2] ? 1 : 0) + (occupied[i][3] ? 1 : 0);
			tree.update(i, i, total);
		}
		int[] toLeft = new int[willExit];
		int[] delta = new int[willExit];
		for (int i = 0; i < willExit; i++) {
			int row = Integer.parseInt(order[i].substring(0, order[i].length() - 1)) - 1;
			int seat = order[i].charAt(order[i].length() - 1) - 'A';
			occupied[row][seat] = false;
			int base = 0;
			if (seat < 3) {
				for (int j = seat; j <= 3; j++) {
					if (occupied[row][j]) {
						base++;
					}
				}
			} else {
				for (int j = seat; j >= 2; j--) {
					if (occupied[row][j]) {
						base++;
					}
				}
			}
			toLeft[i] = (int) (base + tree.query(0, row - 1));
			delta[i] = (int) (base + tree.query(row + 1, count - 1)) - toLeft[i];
			if (seat == 2 || seat == 3) {
				tree.update(row, row, -1);
			}
		}
		BigInteger current = BigInteger.valueOf(ArrayUtils.sumArray(toLeft));
		long goLeft = willExit;
		long goRight = 0;
		BigInteger answer = current.multiply(passCost).add(
			BigInteger.valueOf(goLeft * (goLeft - 1) / 2).add(BigInteger.valueOf(goRight * (goRight - 1) / 2)).multiply(waitCost));
		int[] dOrder = ArrayUtils.order(delta);
		for (int i : dOrder) {
			goLeft--;
			goRight++;
			current = current.add(BigInteger.valueOf(delta[i]));
			answer = answer.min(current.multiply(passCost).add(
						BigInteger.valueOf(goLeft * (goLeft - 1) / 2).add(BigInteger.valueOf(goRight * (goRight - 1) / 2)).multiply(waitCost)));
		}
		out.printLine(answer);
    }
}
