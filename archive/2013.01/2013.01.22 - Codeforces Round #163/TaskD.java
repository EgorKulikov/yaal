package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int edgeCount = in.readInt();
        int[] from = new int[edgeCount];
        int[] to = new int[edgeCount];
        int[] weight = new int[edgeCount];
        IOUtils.readIntArrays(in, from, to, weight);
        MiscUtils.decreaseByOne(from, to);
        int[][] distance = new int[count][count];
        ArrayUtils.fill(distance, Integer.MAX_VALUE);
        for (int i = 0; i < count; i++)
            distance[i][i] = 0;
        for (int i = 0; i < edgeCount; i++)
            distance[from[i]][to[i]] = distance[to[i]][from[i]] = weight[i];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                for (int k = 0; k < count; k++) {
                    if (distance[j][i] != Integer.MAX_VALUE && distance[i][k] != Integer.MAX_VALUE)
                        distance[j][k] = Math.min(distance[j][k], distance[j][i] + distance[i][k]);
                }
            }
        }
        final int[] position = new int[count];
        final int[] leftValue = new int[count];
        final int[] rightValue = new int[count];
        int[] value = new int[count];
        int[] order = ArrayUtils.createOrder(count);
        int[] leftOrder = ArrayUtils.createOrder(count);
        int[] rightOrder = ArrayUtils.createOrder(count);
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < edgeCount; i++) {
            for (int j = 0; j < count; j++) {
                int a = distance[from[i]][j] * 2;
                int b = distance[to[i]][j] * 2;
                if (a - b >= weight[i] * 2) {
                    position[j] = 0;
                    leftValue[j] = b + weight[i] * 2;
                    rightValue[j] = b;
                } else if (b - a >= weight[i] * 2) {
                    position[j] = 2 * weight[i];
                    leftValue[j] = a;
                    rightValue[j] = a + 2 * weight[i];
                } else {
                    position[j] = weight[i] + (b - a) / 2;
                    leftValue[j] = a;
                    rightValue[j] = b;
                }
            }
            ArrayUtils.sort(order, new IntComparator() {
                public int compare(int first, int second) {
                    return position[first] - position[second];
                }
            });
            ArrayUtils.sort(leftOrder, new IntComparator() {
                public int compare(int first, int second) {
                    return leftValue[second] - leftValue[first];
                }
            });
            ArrayUtils.sort(rightOrder, new IntComparator() {
                public int compare(int first, int second) {
                    return rightValue[second] - rightValue[first];
                }
            });
            int k = 0;
            for (int j : leftOrder) {
                for (; k < count && position[order[k]] <= position[j]; k++)
                    value[order[k]] = leftValue[j] + position[order[k]];
            }
            k = count - 1;
            for (int j : rightOrder) {
                for (; k >= 0 && position[order[k]] >= position[j]; k--)
                    value[order[k]] = Math.max(value[order[k]], rightValue[j] + 2 * weight[i] - position[order[k]]);
            }
            for (int j : order)
                answer = Math.min(answer, value[j]);
            for (int j = 1; j < count; j++)
                answer = Math.min(answer, (value[order[j]] + value[order[j - 1]] + position[order[j - 1]] - position[order[j]]) >> 1);
        }
        out.printLine(answer / 2d);
    }

    private int getDistance(int[][] distance, int left, int leftDelta, int right, int rightDelta) {
        int result = 0;
        for (int i = 0; i < distance.length; i++)
            result = Math.max(result, Math.min(2 * distance[left][i] + leftDelta, 2 * distance[right][i] + rightDelta));
        return result;
    }
}
