package net.egork.collections;

import net.egork.generated.collections.comparator.IntComparator;
import net.egork.misc.ArrayUtils;

/**
 * @author egor@egork.net
 */
public class KDTree2D {
    private final int[] weight;
    private final int[] x;
    private final int[] y;
    private final int[] at;
    private final int[] maX;
    private final int[] maY;
    private final int[] miX;
    private final int[] miY;

    public KDTree2D(int[] x, int[] y, int[] startWeight) {
        this.x = x.clone();
        this.y = y.clone();
        int nodeCount = Math.max(1, Integer.highestOneBit(x.length) << 2);
        weight = new int[nodeCount];
        maX = new int[nodeCount];
        maY = new int[nodeCount];
        miX = new int[nodeCount];
        miY = new int[nodeCount];
        int[] order = ArrayUtils.createOrder(x.length);
        init(0, 0, x.length - 1, true, startWeight, order);
        at = ArrayUtils.reversePermutation(order);
        ArrayUtils.orderBy(at.clone(), this.x, this.y);
    }

    private void init(int root, int from, int to, final boolean byX, int[] startWeight, int[] order) {
        if (from != to) {
            ArrayUtils.sort(order, from, to + 1, new IntComparator() {
                @Override
                public int compare(int first, int second) {
                    if (byX) {
                        return x[first] < x[second] ? -1 : x[first] > x[second] ? 1 : 0;
                    } else {
                        return y[first] < y[second] ? -1 : y[first] > y[second] ? 1 : 0;
                    }
                }
            });
            int middle = (from + to) >> 1;
            init(2 * root + 1, from, middle, !byX, startWeight, order);
            init(2 * root + 2, middle + 1, to, !byX, startWeight, order);
            weight[root] = weight[2 * root + 1] + weight[2 * root + 2];
            miX[root] = Math.min(miX[2 * root + 1], miX[2 * root + 2]);
            maX[root] = Math.max(maX[2 * root + 1], maX[2 * root + 2]);
            miY[root] = Math.min(miY[2 * root + 1], miY[2 * root + 2]);
            maY[root] = Math.max(maY[2 * root + 1], maY[2 * root + 2]);
        } else {
            weight[root] = startWeight[order[from]];
            miX[root] = maX[root] = x[order[from]];
            miY[root] = maY[root] = y[order[from]];
        }
    }

    public void changeWeight(int index, int newWeight) {
        changeWeight(0, 0, x.length - 1, at[index], newWeight);
    }

    private void changeWeight(int root, int from, int to, int at, int newWeight) {
        if (from > at || to < at) {
            return;
        }
        if (from == to) {
            weight[root] = newWeight;
        } else {
            int middle = (from + to) >> 1;
            changeWeight(2 * root + 1, from, middle, at, newWeight);
            changeWeight(2 * root + 2, middle + 1, to, at, newWeight);
            weight[root] = weight[2 * root + 1] + weight[2 * root + 2];
        }
    }

    public int query(int fX, int fY, int tX, int tY) {
        return query(0, 0, x.length - 1, fX, fY, tX, tY);
    }

    private int query(int root, int from, int to, int fX, int fY, int tX, int tY) {
        if (fX <= miX[root] && fY <= miY[root] && maX[root] <= tX && maY[root] <= tY) {
            return weight[root];
        }
        if (fX > maX[root] || fY > maY[root] || tX < miX[root] || tY < miY[root]) {
            return 0;
        }
        int middle = (from + to) >> 1;
        return query(2 * root + 1, from, middle, fX, fY, tX, tY) + query(2 * root + 2, middle + 1, to, fX, fY, tX, tY);
    }
}
