package on2012_09.on2012_09_10_Russian_CodeCup_Finals.A_____________;



import net.egork.misc.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long length = in.readInt();
		int rightCount = in.readInt();
		final long[] right = IOUtils.readLongArray(in, rightCount);
		int leftCount = in.readInt();
		final long[] left = IOUtils.readLongArray(in, leftCount);
		int[] rightOrder = ArrayUtils.order(right);
		int[] leftOrder = ArrayUtils.order(left);
		int[] all = new int[leftCount + rightCount];
		for (int i = 0; i < leftCount; i++)
			all[i] = -i - 1;
		for (int i = 0; i < rightCount; i++)
			all[i + leftCount] = i;
		ArrayUtils.sort(all, new IntComparator() {
			public int compare(int first, int second) {
				long leftValue = first < 0 ? left[-first - 1] : right[first];
				long rightValue = second < 0 ? left[-second - 1] : right[second];
				return IntegerUtils.longCompare(leftValue, rightValue);
			}
		});
		long[] leftAnswer = new long[leftCount];
		long[] rightAnswer = new long[rightCount];
		int leftFirst = 0;
		int rightFirst = all.length - 1;
		int leftBegin = 0;
		int leftEnd = leftCount - 1;
		int rightBegin = 0;
		int rightEnd = rightCount - 1;
		long delta = 0;
		long time = 0;
		for (int i = 0; i < leftCount + rightCount; i++) {
			boolean isLeft = false;
			boolean isRight = false;
			long currentTime;
			if ((i & 1) == 0) {
				if (leftBegin > leftEnd)
					isRight = true;
				else if (rightBegin > rightEnd)
					isLeft = true;
				else {
					isLeft = left[leftOrder[leftBegin]] <= length - right[rightOrder[rightEnd]];
					isRight = left[leftOrder[leftBegin]] >= length - right[rightOrder[rightEnd]];
				}
				if (isLeft) {
					currentTime = left[leftOrder[leftBegin++]];
					if (isRight)
						rightEnd--;
				} else
					currentTime = length - right[rightOrder[rightEnd--]];
				currentTime -= delta;
			} else {
				if (leftBegin > leftEnd)
					isLeft = true;
				else if (rightBegin > rightEnd)
					isRight = true;
				else {
					isLeft = length - left[leftOrder[leftEnd]] >= right[rightOrder[rightBegin]];
					isRight = length - left[leftOrder[leftEnd]] <= right[rightOrder[rightBegin]];
				}
				if (isLeft) {
					currentTime = right[rightOrder[rightBegin++]];
					if (isRight)
						leftEnd--;
				} else
					currentTime = length - left[leftOrder[leftEnd--]];
				currentTime += delta;
			}
			int current;
			if (isLeft)
				current = all[leftFirst++];
			else
				current = all[rightFirst--];
			time += currentTime;
			if (current < 0)
				leftAnswer[-current - 1] = time;
			else
				rightAnswer[current] = time;
			if ((i & 1) == 0)
				delta += currentTime;
			else
				delta -= currentTime;
			if (isLeft && isRight) {
				current = all[rightFirst--];
				if (current < 0)
					leftAnswer[-current - 1] = time;
				else
					rightAnswer[current] = time;
				i++;
			}
		}
		out.printLine(Array.wrap(rightAnswer).toArray());
		out.printLine(Array.wrap(leftAnswer).toArray());
	}
}
