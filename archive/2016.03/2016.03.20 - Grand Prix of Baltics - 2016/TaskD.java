package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();

        Map<Triangle, List<Integer>[]> num = new HashMap<>();

        for (int i = 0; i < n; i++) {
            long x1 = in.readInt();
            long y1 = in.readInt();
            long x2 = in.readInt();
            long y2 = in.readInt();
            long x3 = in.readInt();
            long y3 = in.readInt();
            long a = dist(x1, y1, x2, y2);
            long b = dist(x2, y2, x3, y3);
            long c = dist(x3, y3, x1, y1);
            long vp = (x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1);
            if (vp > 0) {
                long t = a; a = b; b = t;
            }
            Triangle triangle;
            if (a >= b && a >= c) {
                triangle = new Triangle(a, b, c);
            } else if (b >= a && b >= c) {
                triangle = new Triangle(b, c, a);
            } else {
                triangle = new Triangle(c, a, b);
            }
            boolean isLeft = true;
            if (triangle.b > triangle.c) {
                long t = triangle.b;
                triangle.b = triangle.c;
                triangle.c = t;
                isLeft = false;
            }
            List<Integer>[] nn = num.get(triangle);
            if (nn == null) {
                nn = new List[2];
                nn[0] = new ArrayList<>();
                nn[1] = new ArrayList<>();
                num.put(triangle, nn);
            }
            if (triangle.b == triangle.c) {
                if (nn[0].size() > nn[1].size()) {
                    isLeft = false;
                }
            }
            if (isLeft) {
                nn[0].add(i + 1);
            } else {
                nn[1].add(i + 1);
            }
        }
        List<String> res = new ArrayList<>();
        for (List<Integer>[] lists : num.values()) {
//            System.out.println(Arrays.toString(lists));
            for (int i = 0; i < Math.max(lists[0].size(), lists[1].size()); i++) {
                int a = i < lists[0].size() ? lists[0].get(i) : 0;
                int b = i < lists[1].size() ? lists[1].get(i) : 0;
                res.add(a + "-" + b);
            }
        }
        out.printLine(res.size());
        for (String s : res) {
            out.printLine(s);
        }
    }

    private long dist(long x1, long y1, long x2, long y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    class Triangle {
        long a, b, c;

        public Triangle(long a, long b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Triangle triangle = (Triangle) o;

            if (a != triangle.a) {
                return false;
            }
            if (b != triangle.b) {
                return false;
            }
            return c == triangle.c;

        }

        @Override
        public int hashCode() {
            int result = (int) (a ^ (a >>> 32));
            result = 31 * result + (int) (b ^ (b >>> 32));
            result = 31 * result + (int) (c ^ (c >>> 32));
            return result;
        }
    }
}
