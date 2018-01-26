package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.*;

public class TaskC {
    private static final int BUBEN = 200;
    private double min;
    private double max;
    private Line line;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        Point[] points = new Point[n];
        int[] action = new int[n];
        int[] remove = new int[n];
        Line[] lines = new Line[n];
        for (int i = 0; i < n; i++) {
            char s = in.readCharacter();
            if (s == '+') {
                action[i] = 0;
                points[i] = new Point(in.readInt(), in.readInt());
            } else if (s == '-') {
                action[i] = 1;
                remove[i] = in.readInt() - 1;
            } else if (s == '?') {
                action[i] = 2;
                lines[i] = new Line(in.readInt(), in.readInt(), in.readInt());
            }
        }

        int l = 0;
        int r = 0;
        List<Point> ch = null;
        boolean[] take = new boolean[n];
        for (int op = 0; op < n; op++) {
            if (op % BUBEN == 0) {
                l = op;
                r = Math.min(l + BUBEN, n);
                // rebuild convex hull
                for (int i = 0; i < l; i++) {
                    if (action[i] == 0) take[i] = true;
                }
                for (int i = 0; i < r; i++) {
                    if (action[i] == 1) take[remove[i]] = false;
                }
                List<Point> pp = new ArrayList<>();
                for (int i = 0; i < l; i++) {
                    if (take[i]) {
                        pp.add(points[i]);
                    }
                }
                ch = convexHull(pp);
            }
            if (action[op] == 2) {
                line = lines[op];
                min = Double.POSITIVE_INFINITY;
                max = Double.NEGATIVE_INFINITY;
                List<Point> add = new ArrayList<>();
                for (int i = l; i < op; i++) {
                    take[i] = false;
                    if (action[i] == 0) {
                        take[i] = true;
                    } else if (action[i] == 1 && remove[i] >= l) {
                        take[remove[i]] = false;
                    }
                }
                for (int i = l; i < op; i++) {
                    if (take[i]) add.add(points[i]);
                }
                for (int i = op; i < r; i++) {
                    if (action[i] == 1 && remove[i] < l) {
                        add.add(points[remove[i]]);
                    }
                }
                if (ch.size() < 3) {
                    add.addAll(ch);
                    add = convexHull(add);
                    checkConvexHull(add);
                } else {
                    checkConvexHull(ch);
                    add = convexHull(add);
                    checkConvexHull(add);
                    for (Point point : add) {
                        Point c1 = ch.get(getMax(ch, new Comparator<Point>() {
                            @Override
                            public int compare(Point o1, Point o2) {
                                return Long.signum(vp(o1, o2, point));
                            }
                        }));
                        checkSegment(point, c1);
                        Point c2 = ch.get(getMax(ch, new Comparator<Point>() {
                            @Override
                            public int compare(Point o1, Point o2) {
                                return -Long.signum(vp(o1, o2, point));
                            }
                        }));
                        checkSegment(point, c2);
                    }
                }
                if (max > min) {
                    out.printLine(max - min);
                } else {
                    out.printLine(0);
                }
            }
        }
    }

    private void checkConvexHull(List<Point> ch) {
        int n = ch.size();
        if (n < 2) {
            return;
        }
        int maxPoint = getMax(ch, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                long d1 = line.a * o1.x + line.b * o1.y;
                long d2 = line.a * o2.x + line.b * o2.y;
                return Long.compare(d1, d2);
            }
        });
        int minPoint = getMax(ch, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                long d1 = line.a * o1.x + line.b * o1.y;
                long d2 = line.a * o2.x + line.b * o2.y;
                return -Long.compare(d1, d2);
            }
        });
        long d1 = line.dist(ch.get(minPoint));
        long d2 = line.dist(ch.get(maxPoint));
        if (d1 == 0) {
            checkSegment(ch.get(minPoint), ch.get((minPoint + 1) % n));
            checkSegment(ch.get(minPoint), ch.get((minPoint + n - 1) % n));
        } else if (d2 == 0) {
            checkSegment(ch.get(maxPoint), ch.get((maxPoint + 1) % n));
            checkSegment(ch.get(maxPoint), ch.get((maxPoint + n - 1) % n));
        } else {
            if (Long.signum(d1) == Long.signum(d2)) return;
            int l = minPoint;
            int r = maxPoint;
            if (r < l) r += n;
            while (r != l + 1) {
                int m = (l + r) / 2;
                long d = line.dist(ch.get(m % n));
                if (d < 0) l = m; else r = m;
            }
            checkSegment(ch.get(l % n), ch.get(r % n));
            l = maxPoint;
            r = minPoint;
            if (r < l) r += n;
            while (r != l + 1) {
                int m = (l + r) / 2;
                long d = line.dist(ch.get(m % n));
                if (d > 0) l = m; else r = m;
            }
            checkSegment(ch.get(l % n), ch.get(r % n));
        }
    }

    private void checkSegment(Point a, Point b) {
        long da = line.dist(a);
        long db = line.dist(b);
        if (da == 0) check(line.proj(a));
        if (db == 0) check(line.proj(b));
        if (Long.signum(da) == Long.signum(db)) return;
        da = Math.abs(da);
        db = Math.abs(db);
        double pa = line.proj(a);
        double pb = line.proj(b);
        check((pa * db + pb * da) / (da + db));
    }

    private void check(double c) {
        if (max < c) max = c;
        if (min > c) min = c;
    }

    private int getMax(List<Point> points, Comparator<Point> comparator) {
        int l = 0;
        int r = points.size();
        while (true) {
            if (r - l < 4) {
                int max = l;
                for (int i = l + 1; i < r; i++) {
                    if (comparator.compare(points.get(i), points.get(max)) > 0) {
                        max = i;
                    }
                }
                return max;
            } else {
                int pp = comparator.compare(points.get(l), points.get(l + 1));
                if (pp == 0) {
                    l++;
                    continue;
                }
                int m = (l + r) / 2;
                int p2 = comparator.compare(points.get(m), points.get(m + 1));
                int p3 = comparator.compare(points.get(l), points.get(m));
                if (pp > 0) {
                    if (p2 > 0) {
                        if (p3 > 0) {
                            l = m;
                        } else {
                            r = m + 1;
                        }
                    } else {
                        r = m + 1;
                    }
                } else {
                    if (p2 >= 0) {
                        l = m;
                    } else {
                        if (p3 < 0) {
                            l = m;
                        } else {
                            r = m + 1;
                        }
                    }
                }
            }
        }
    }

    private List<Point> convexHull(List<Point> points) {
        if (points.size() == 0) return points;
        Point min = points.get(0);
        int n = points.size();
        for (int i = 0; i < n; i++) {
            Point point = points.get(i);
            if (point.y < min.y || point.y == min.y && point.x < min.x) {
                min = point;
            }
        }
        final Point finalMin = min;
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                long x1 = o1.x - finalMin.x;
                long y1 = o1.y - finalMin.y;
                long x2 = o2.x - finalMin.x;
                long y2 = o2.y - finalMin.y;
                long vp = x1 * y2 - x2 * y1;
                if (vp == 0) {
                    long d1 = x1 * x1 + y1 * y1;
                    long d2 = x2 * x2 + y2 * y2;
                    return Long.compare(d1, d2);
                }
                return -Long.signum(vp);
            }
        });
        List<Point> res = new ArrayList<>();
        Point last = null;
        for (Point point : points) {
            if (point.equals(last)) continue;
            last = point;
            while (res.size() >= 2 &&
                    vp(res.get(res.size() - 1), point, res.get(res.size() - 2)) <= 0) {
                res.remove(res.size() - 1);
            }
            res.add(point);
        }
        return res;
    }

    long vp(Point o1, Point o2, Point finalMin) {
        long x1 = o1.x - finalMin.x;
        long y1 = o1.y - finalMin.y;
        long x2 = o2.x - finalMin.x;
        long y2 = o2.y - finalMin.y;
        return x1 * y2 - x2 * y1;
    }

    class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Point point = (Point) o;

            if (x != point.x) {
                return false;
            }
            return y == point.y;

        }

        @Override
        public int hashCode() {
            int result = (int) (x ^ (x >>> 32));
            result = 31 * result + (int) (y ^ (y >>> 32));
            return result;
        }
    }

    class Line {
        long a, b, c;
        double da, db;

        public Line(long a, long b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
            da = a;
            db = b;
            double dd = Math.hypot(da, db);
            da /= dd;
            db /= dd;
        }

        public long dist(Point point) {
            return a * point.x + b * point.y + c;
        }

        public double proj(Point point) {
            return -db * point.x + da * point.y;
        }
    }
}
