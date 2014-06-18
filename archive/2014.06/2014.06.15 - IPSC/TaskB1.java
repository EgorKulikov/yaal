package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskB1 {
	int r = 43;
	int s = 22;
	long mod = 1L << 32;
	List<Long> x;
	IntList c;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] state = new int[count];
		for (int i = 0; i < count; i++) {
			long value = in.readLong();
			if (value != 0) {
				if (Long.bitCount(value) != 1)
					throw new RuntimeException();
				state[i] = Long.bitCount(value - 1);
			}
		}
		x = new ArrayList<>();
		for (int i = 0; i <= 42; i++)
			x.add(in.readLong());
		c = new IntArrayList();
		for (int i = 0; i <= 42; i++)
			c.add(0);
		int moveCount = in.readInt();
		for (int i = 0; i < moveCount; i++) {
			char move = in.readCharacter();
			boolean stateChanged = false;
			boolean canAdd = false;
			int length;
			if (move == 'l') {
				int at = 0;
				for (int j = 0; j < count; j++) {
					if (state[j] != 0) {
						if (canAdd && state[at - 1] == state[j]) {
							state[at - 1]++;
							canAdd = false;
							stateChanged = true;
						} else {
							if (at != j)
								stateChanged = true;
							state[at++] = state[j];
							canAdd = true;
						}
					}
				}
				length = at;
				Arrays.fill(state, at, count, 0);
			} else {
				int at = count - 1;
				for (int j = count - 1; j >= 0; j--) {
					if (state[j] != 0) {
						if (canAdd && state[at + 1] == state[j]) {
							state[at + 1]++;
							canAdd = false;
							stateChanged = true;
						} else {
							if (at != j)
								stateChanged = true;
							state[at--] = state[j];
							canAdd = true;
						}
					}
				}
				length = count - at - 1;
				Arrays.fill(state, 0, at + 1, 0);
			}
			if (stateChanged && length != count) {
				int position = (int) (random() % (count - length));
				if (move == 'l')
					position += length;
				int value = 1;
				if (random() % 10 == 0)
					value = 2;
				state[position] = value;
			}
		}
		for (int i = 0; i < count; i++) {
			if (i != 0)
				out.print(' ');
			if (state[i] == 0)
				out.print(0);
			else
				out.print(BigInteger.ONE.shiftLeft(state[i]));
		}
		out.printLine();
	}

	long random() {
		long next = x.get(x.size() - s) - x.get(x.size() - r) - c.get(x.size() - 1);
		if (next < 0)
			c.add(1);
		else
			c.add(0);
		if (next < 0)
			next += mod;
		x.add(next);
		return next;
	}
}
