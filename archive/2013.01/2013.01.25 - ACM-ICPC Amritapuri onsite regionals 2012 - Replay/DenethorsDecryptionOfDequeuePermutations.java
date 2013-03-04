package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

public class DenethorsDecryptionOfDequeuePermutations {
	static final int MOD = (int) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
//		try {
		int count = in.readInt();
		int queryCount = count >> 1;
		int[] power = new int[count];
		power[count - 2] = 1;
		for (int i = count - 3; i >= 0; i--) {
			power[i] = power[i + 1] << 1;
			if (power[i] >= MOD)
				power[i] -= MOD;
		}
		int[] sumPower = new int[count + 1];
		for (int i = 0; i < count; i++) {
			sumPower[i + 1] = sumPower[i] + power[i];
			if (sumPower[i + 1] >= MOD)
				sumPower[i + 1] -= MOD;
		}
		int min = 0;
		int max = sumPower[count];
		int[] up = new int[count];
		int[] down = new int[count];
		NavigableSet<Integer> set = new TreeSet<Integer>();
		set.add(0);
		set.add(count - 1);
		boolean consistent = true;
		for (int i = 0; i < queryCount; i++) {
			int position = in.readInt();
			int value = in.readInt() - 1;
			if (!consistent) {
				out.printLine(-1);
				continue;
			}
			if (value >= position && value < count - position)
				consistent = false;
			if (!consistent) {
				out.printLine(-1);
				continue;
			}
			int lastUp, lastDown;
			if (value < position) {
				lastUp = value;
				lastDown = position - 1 - lastUp;
			} else {
				lastDown = count - value - 1;
				lastUp = position - 1 - lastDown;
			}
			int previous = set.floor(position - 1);
			if (up[previous] > lastUp || down[previous] > lastDown) {
				consistent = false;
				out.printLine(-1);
				continue;
			}
			int nextUp = lastUp;
			int nextDown = lastDown;
			if (value < position)
				nextUp++;
			else
				nextDown++;
			int next = set.ceiling(position);
			if (next != count - 1 && (up[next] < nextUp || down[next] < nextDown)) {
				consistent = false;
				out.printLine(-1);
				continue;
			}
			if (next == count - 1) {
				max -= sumPower[count - 1] - sumPower[previous];
				if (max < 0)
					max += MOD;
				if (max >= MOD)
					max -= MOD;
			} else {
				max -= sumPower[previous + down[next] - down[previous]] - sumPower[previous];
				if (max < 0)
					max += MOD;
				if (max >= MOD)
					max -= MOD;
				min -= sumPower[next] - sumPower[next - (down[next] - down[previous])];
				if (min < 0)
					min += MOD;
				if (min >= MOD)
					min -= MOD;
			}
			up[position - 1] = lastUp;
			down[position - 1] = lastDown;
			up[position] = nextUp;
			down[position] = nextDown;
			set.add(position - 1);
			set.add(position);
			if (value >= position) {
				min += power[position - 1];
				if (min >= MOD)
					min -= MOD;
				max += power[position - 1];
				if (max >= MOD)
					max -= MOD;
			}
			max += sumPower[previous + lastDown - down[previous]] - sumPower[previous];
			if (max < 0)
				max += MOD;
			if (max >= MOD)
				max -= MOD;
			min += sumPower[position - 1] - sumPower[position - 1 - (lastDown - down[previous])];
			if (min < 0)
				min += MOD;
			if (min >= MOD)
				min -= MOD;
			if (next == count - 1) {
				max += sumPower[count - 1] - sumPower[position];
				if (max < 0)
					max += MOD;
				if (max >= MOD)
					max -= MOD;
			} else {
				max += sumPower[position + down[next] - nextDown] - sumPower[position];
				if (max < 0)
					max += MOD;
				if (max >= MOD)
					max -= MOD;
				min += sumPower[next] - sumPower[next - (down[next] - nextDown)];
				if (min < 0)
					min += MOD;
				if (min >= MOD)
					min -= MOD;
			}
			out.printLine(min, max);
		}
//		} catch (IndexOutOfBoundsException e){}
    }
}
