package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class RestaurantRating {
    int[] queue;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        final int[] rating = new int[count];
        int reviewCount = 0;
        for (int i = 0; i < count; i++) {
            int type = in.readInt();
            if (type == 1) {
                rating[i] = in.readInt();
                reviewCount++;
            } else {
                rating[i] = -1;
            }
        }
        int[] reviews = new int[reviewCount];
        int index = 0;
        for (int i = 0; i < count; i++) {
            if (rating[i] != -1)
                reviews[index++] = i;
        }
        ArrayUtils.sort(reviews, new IntComparator() {
            public int compare(int first, int second) {
                return rating[second] - rating[first];
            }
        });
        queue = new int[2 * count];
        int[] reverse = new int[count];
        for (int i = 0; i < reviewCount; i++)
            reverse[reviews[i]] = i;
        int[] next = new int[reviewCount];
        int[] last = new int[reviewCount];
        for (int i = 0; i < reviewCount; i++)
            next[i] = i + 1;
        for (int i = 0; i < reviewCount; i++)
            last[i] = i - 1;
        boolean[] enabled = new boolean[reviewCount];
        Arrays.fill(enabled, true);
        int position = reviewCount / 3 - 1;
        int left = position + 1;
        int right = reviewCount - left;
        int[] answer = new int[count - reviewCount];
        index = answer.length - 1;
        for (int i = count - 1; i >= 0; i--) {
            if (rating[i] == -1) {
                if (position == -1)
                    answer[index--] = -1;
                else {
                    answer[index--] = rating[reviews[position]];
                }
            } else {
                int removePosition = reverse[i];
                enabled[removePosition] = false;
                if (removePosition < position)
                    left--;
                else
                    right--;
                if (position == removePosition)
                    position = get(position, next, enabled);
                if (2 * left > right) {
                    position = get(position - 1, last, enabled);
                    left--;
                    right++;
                } else if (2 * (left + 1) < right) {
                    position = get(position + 1, next, enabled);
                    left++;
                    right--;
                }
            }
        }
        for (int i : answer) {
            if (i == -1)
                out.printLine("No reviews yet");
            else
                out.printLine(i);
        }
	}

    private int get(int position, int[] next, boolean[] enabled) {
        int size = 0;
        while (position >= 0 && position < enabled.length && !enabled[position]) {
            queue[size++] = position;
            position = next[position];
        }
        for (int i = 0; i < size; i++)
            next[queue[i]] = position;
        return position;
    }
}
