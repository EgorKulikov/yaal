package net.egork;

public class FlattenOut {
	public long[] simulateIt(long[] height, long T) {
		long[] next = new long[height.length];
		while (T != 0) {
			long max = Long.MIN_VALUE;
			long min = Long.MAX_VALUE;
			long nextEvent = T;
			int count = height.length;
			for (int i = 0; i < count; i++) {
				max = Math.max(max, height[i]);
				min = Math.min(min, height[i]);
				if (height[(i + count - 1) % count] <= 0 && height[i] > 0)
					nextEvent = Math.min(nextEvent, height[i]);
				else if (height[(i + count - 1) % count] > 0 && height[i] <= 0)
					nextEvent = Math.min(nextEvent, -height[i] + 1);
			}
			if (max <= 0 || min > 0)
				return height;
			if (min == 0 && max == 1) {
				long[] result = new long[count];
				for (int i = 0; i < count; i++)
					result[((int) ((i + T) % count))] = height[i];
				return result;
			}
			for (int i = 0; i < count; i++) {
				if (height[(i + count - 1) % count] <= 0 && height[i] > 0)
					next[i] = height[i] - nextEvent;
				else if (height[(i + count - 1) % count] > 0 && height[i] <= 0)
					next[i] = height[i] + nextEvent;
				else
					next[i] = height[i];
			}
			long[] temp = next;
			next = height;
			height = temp;
			T -= nextEvent;
		}
		return height;
	}


}

