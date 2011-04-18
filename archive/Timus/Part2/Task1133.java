package Timus.Part2;

import net.egork.collections.Function;
import net.egork.misc.MiscUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1133 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int firstIndex = in.readInt();
		long firstValue = in.readLong();
		int secondIndex = in.readInt();
		long secondValue = in.readLong();
		int targetIndex = in.readInt();
		if (firstIndex > secondIndex) {
			int temp = firstIndex;
			firstIndex = secondIndex;
			secondIndex = temp;
			long longTemp = firstValue;
			firstValue = secondValue;
			secondValue = longTemp;
		}
		secondIndex -= firstIndex;
		targetIndex -= firstIndex;
		final long startValue = firstValue;
		final int finalSecondIndex = secondIndex;
		final long finalSecondValue = secondValue;
		long nextValue = MiscUtils.binarySearch(-2000000000, 2000000000, new Function<Long, Boolean>() {
			@Override
			public Boolean value(Long argument) {
				long lastValue = startValue;
				long currentValue = argument;
				for (int i = 1; i < finalSecondIndex; i++) {
					long nextValue = lastValue + currentValue;
					if (nextValue > 2000000000)
						return true;
					if (nextValue < -2000000000)
						return false;
					lastValue = currentValue;
					currentValue = nextValue;
				}
				return currentValue >= finalSecondValue;
			}
		});
		if (targetIndex > 0) {
			long lastValue = startValue;
			long currentValue = nextValue;
			for (int i = 1; i < targetIndex; i++) {
				long value = lastValue + currentValue;
				lastValue = currentValue;
				currentValue = value;
			}
			out.println(currentValue);
		} else {
			long lastValue = nextValue;
			long currentValue = startValue;
			for (int i = 0; i > targetIndex; i--) {
				long value = lastValue - currentValue;
				lastValue = currentValue;
				currentValue = value;
			}
			out.println(currentValue);
		}
	}
}

