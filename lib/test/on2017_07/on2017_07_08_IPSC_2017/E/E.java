package on2017_07.on2017_07_08_IPSC_2017.E;





import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.NavigableSet;
import java.util.TreeSet;

import static net.egork.misc.MiscUtils.MOD9;

public class E {
    static class Segment implements Comparable<Segment> {
        int left;
        int right;
        int delta;

        public Segment(int left, int right, int delta) {
            this.left = left;
            this.right = right;
            this.delta = delta;
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(left, o.left);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        System.err.println(testNumber);
        NavigableSet<Segment> segments = new TreeSet<>();
        segments.add(new Segment(-2000000000, 2000000000, 0));
        int n = in.readInt();
        long answer = 0;
//        int[] height = new int[20000];
        for (int i = 0; i < n; i++) {
            int at = in.readInt();
            int delta = in.readInt();
            Segment key = new Segment(at, at, 0);
            Segment toLeft = segments.lower(key);
            Segment toRight = segments.floor(key);
            if (toLeft.equals(toRight)) {
                segments.remove(toLeft);
                toLeft = new Segment(toLeft.left, at, toLeft.delta);
                toRight = new Segment(at, toRight.right, toRight.delta);
                segments.add(toLeft);
                segments.add(toRight);
            }
            long current = 0;
            int from = at;
            int to = at;
            if (toLeft.delta * delta <= 0) {
                current++;
                if (toLeft.left == at - 1) {
                    toLeft.delta += delta;
                } else {
                    Segment left1 = new Segment(toLeft.left, at - 1, toLeft.delta);
                    Segment left2 = new Segment(at - 1, at, toLeft.delta + delta);
                    segments.remove(toLeft);
                    segments.add(left1);
                    segments.add(left2);
                    toLeft = left2;
                }
            } else {
                from = toLeft.left;
                current += toLeft.right - toLeft.left + 1;
                Segment leftToLeft = segments.lower(toLeft);
                if (leftToLeft.left + 1 == leftToLeft.right) {
                    if (leftToLeft.delta == -delta) {
                        leftToLeft.delta = 0;
                    } else {
                        segments.remove(leftToLeft);
                        toLeft.left--;
                    }
                } else {
                    leftToLeft.right--;
                    if (leftToLeft.delta == -delta) {
                        segments.add(new Segment(toLeft.left - 1, toLeft.left, 0));
                    } else {
                        toLeft.left--;
                    }
                }
            }
            if (toRight.delta * delta >= 0) {
                current++;
                if (toRight.right == at + 1) {
                    toRight.delta -= delta;
                } else {
                    Segment right1 = new Segment(at + 1, toRight.right, toRight.delta);
                    Segment right2 = new Segment(at, at + 1, toRight.delta - delta);
                    segments.remove(toRight);
                    segments.add(right1);
                    segments.add(right2);
                    toRight = right2;
                }
            } else {
                current += toRight.right - toRight.left + 1;
                to = toRight.right;
                Segment rightToRight = segments.higher(toRight);
                if (rightToRight.right - 1 == rightToRight.left) {
                    if (rightToRight.delta == delta) {
                        rightToRight.delta = 0;
                    } else {
                        segments.remove(rightToRight);
                        toRight.right++;
                    }
                } else {
                    rightToRight.left++;
                    if (rightToRight.delta == delta) {
                        segments.add(new Segment(toRight.right, toRight.right + 1, 0));
                    } else {
                        toRight.right++;
                    }
                }
            }
            current--;
            if (to - from + 1 != current) {
                throw new RuntimeException();
            }
/*            for (int j = from + 1; j <= at; j++) {
                if (height[j + 5000] - height[j - 1 + 5000] != delta) {
                    throw new RuntimeException();
                }
            }
            for (int j = at + 1; j <= to; j++) {
                if (height[j + 5000] - height[j - 1 + 5000] != -delta) {
                    throw new RuntimeException();
                }
            }
            if (height[from + 5000] - height[from - 1 + 5000] == delta) {
                throw new RuntimeException();
            }
            if (height[to + 1 + 5000] - height[to + 5000] == -delta) {
                throw new RuntimeException();
            }
            for (int j = from; j <= to; j++) {
                height[j + 5000] += delta;
            }
            for (int j = 1; j < height.length; j++) {
                Segment segment = segments.lower(new Segment(j - 5000, j - 5000, 0));
                if (segment.delta != height[j] - height[j - 1]) {
                    throw new RuntimeException();
                }
            }*/
//            System.err.println(current);
            answer += (i + 1L) * current;
            answer %= MOD9;
            Segment cur = toLeft;
            int skip = 0;
            while (true) {
                Segment leftToLeft = segments.lower(cur);
                if (leftToLeft != null && leftToLeft.delta == cur.delta) {
                    int nLeft = leftToLeft.left;
                    segments.remove(leftToLeft);
                    cur.left = nLeft;
                } else {
                    if (skip == 3 || leftToLeft == null) {
                        break;
                    }
                    skip++;
                    cur = leftToLeft;
                }
            }
            cur = toRight;
            skip = 0;
            while (true) {
                Segment rightToRight = segments.higher(cur);
                if (rightToRight != null && rightToRight.delta == cur.delta) {
                    int nRight = rightToRight.right;
                    segments.remove(rightToRight);
                    cur.right = nRight;
                } else {
                    if (skip == 3 || rightToRight == null) {
                        break;
                    }
                    skip++;
                    cur = rightToRight;
                }
            }
            if (toLeft.delta == toRight.delta) {
                toLeft.right = toRight.right;
                segments.remove(toRight);
            }
/*            int last = -2000000000;
            int ld = 2;
            for (Segment segment : segments) {
                if (segment.left != last) {
                    System.err.println("SAD");
                }
                if (segment.delta == ld) {
                    System.err.println("SAD");
                }
                if (segment.right <= segment.left) {
                    System.err.println("SAD");
                }
                last = segment.right;
                ld = segment.delta;
            }*/
        }
//        System.err.println("");
        out.printLine(answer);
    }
}
