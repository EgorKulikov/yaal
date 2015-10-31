package net.egork;

import java.util.Arrays;

public class TrianglePainting {
    public double expectedArea(int[] x1, int[] y1, int[] x2, int[] y2, int[] prob) {
        int count = x1.length;
        Side[] sides = new Side[3 * count];
        for (int i = 0; i < count; i++) {
            double ang1 = Math.atan2(y1[i], x1[i]);
            double ang2 = Math.atan2(y2[i], x2[i]);
            double dAng = ang1 - ang2;
            while (dAng < 0) {
                dAng += 2 * Math.PI;
            }
            while (dAng > 2 * Math.PI) {
                dAng -= 2 * Math.PI;
            }
            if (dAng < Math.PI) {
                sides[3 * i] = new Side(i, x1[i], y1[i], prob[i]);
                sides[3 * i + 1] = new Side(i, x2[i] - x1[i], y2[i] - y1[i], prob[i]);
                sides[3 * i + 2] = new Side(i, -x2[i], -y2[i], prob[i]);
            } else {
                sides[3 * i] = new Side(i, x2[i], y2[i], prob[i]);
                sides[3 * i + 1] = new Side(i, x1[i] - x2[i], y1[i] - y2[i], prob[i]);
                sides[3 * i + 2] = new Side(i, -x1[i], -y1[i], prob[i]);
            }
        }
        Arrays.sort(sides);
        double answer = 0;
        for (Side current : sides) {
            double yExp = 0;
            for (Side other : sides) {
                if (other == current) {
                    break;
                }
                if (other.id == current.id) {
                    yExp += other.dy;
                } else {
                    yExp += other.dy * other.prob;
                }
            }
            answer += current.dx * (current.dy + 2 * yExp) * current.prob;
        }
        return Math.abs(answer) / 2;
    }

    static class Side implements Comparable<Side> {
        int id;
        int dx;
        int dy;
        double prob;

        public Side(int id, int dx, int dy, int prob) {
            this.id = id;
            this.dx = dx;
            this.dy = dy;
            this.prob = prob / 100d;
        }

        @Override
        public int compareTo(Side o) {
            boolean firstUp = dy > 0 || dy == 0 && dx < 0;
            boolean secondUp = o.dy > 0 || o.dy == 0 && o.dx < 0;
            if (firstUp && !secondUp) {
                return -1;
            }
            if (!firstUp && secondUp) {
                return 1;
            }
            int delta = dx * o.dy - o.dx * dy;
            if (delta != 0) {
                return delta;
            }
            return id - o.id;
        }
    }
}
